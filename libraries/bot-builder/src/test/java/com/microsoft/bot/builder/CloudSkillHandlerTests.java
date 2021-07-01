// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.bot.builder;

import com.microsoft.bot.builder.skills.BotFrameworkSkill;
import com.microsoft.bot.builder.skills.CloudSkillHandler;
import com.microsoft.bot.builder.skills.SkillConversationIdFactoryBase;
import com.microsoft.bot.builder.skills.SkillConversationIdFactoryOptions;
import com.microsoft.bot.builder.skills.SkillConversationReference;
import com.microsoft.bot.connector.authentication.AuthenticationConstants;
import com.microsoft.bot.connector.authentication.BotFrameworkAuthentication;
import com.microsoft.bot.connector.authentication.ClaimsIdentity;
import com.microsoft.bot.restclient.serializer.JacksonAdapter;
import com.microsoft.bot.schema.Activity;
import com.microsoft.bot.schema.ActivityTypes;
import com.microsoft.bot.schema.CallerIdConstants;
import com.microsoft.bot.schema.ConversationAccount;
import com.microsoft.bot.schema.ConversationReference;
import com.microsoft.bot.schema.ResourceResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;


public class CloudSkillHandlerTests {

    private static final String TEST_SKILL_ID = UUID.randomUUID().toString().replace("-", "");
    private static final String TEST_AUTH_HEADER = ""; // Empty since claims extraction is being mocked

    @Test
    public void testSendAndReplyToConversation() {
        List<String[]> theoryCases = new ArrayList<>();
        theoryCases.add(new String[]{ActivityTypes.MESSAGE, null});
        theoryCases.add(new String[]{ActivityTypes.MESSAGE, "replyToId"});
        theoryCases.add(new String[]{ActivityTypes.EVENT, null});
        theoryCases.add(new String[]{ActivityTypes.EVENT, "replyToId"});
        theoryCases.add(new String[]{ActivityTypes.END_OF_CONVERSATION, null});
        theoryCases.add(new String[]{ActivityTypes.END_OF_CONVERSATION, "replyToId"});

        for (String[] theoryCase : theoryCases) {
            String activityType = theoryCase[0];
            String replyToId = theoryCase[1];

            // Arrange
            CloudSkillHandlerTestMocks mockObjects = new CloudSkillHandlerTestMocks();
            Activity activity = new Activity(activityType);
            activity.setReplyToId(replyToId);
            String conversationId =  mockObjects.createAndApplyConversationId(activity).join();

            // Act
            CloudSkillHandler sut = new CloudSkillHandler(
                mockObjects.getAdapter(),
                mockObjects.getBot(),
                mockObjects.getConversationIdFactory(),
                mockObjects.getAuth());

            ResourceResponse response = replyToId == null
                ? sut.handleSendToConversation(TEST_AUTH_HEADER, conversationId, activity).join()
                : sut.handleReplyToActivity(TEST_AUTH_HEADER, conversationId, replyToId, activity).join();

            // Assert
            // Assert the turnContext
            Assert.assertEquals(
                CallerIdConstants.BOT_TO_BOT_PREFIX.concat(TEST_SKILL_ID),
                mockObjects.getTurnContext().getActivity().getCallerId());
            Assert.assertNotNull(
                mockObjects.getTurnContext().getTurnState().get(CloudSkillHandler.SKILL_CONVERSATION_REFERENCE_KEY));

            // Assert based on activity type,
            if (activityType.equals(ActivityTypes.MESSAGE)) {
                // Should be sent to the channel and not to the bot.
                Assert.assertNotNull(mockObjects.getChannelActivity());
                Assert.assertNull(mockObjects.getBotActivity());

                // We should get the resourceId returned by the mock.
                Assert.assertEquals("resourceId", response.getId());

                // Assert the activity sent to the channel.
                Assert.assertEquals(activityType, mockObjects.getChannelActivity().getType());
                Assert.assertNull(mockObjects.getChannelActivity().getCallerId());
                Assert.assertEquals(replyToId, mockObjects.getChannelActivity().getReplyToId());
            } else {
                // Should be sent to the bot and not to the channel.
                Assert.assertNull(mockObjects.getChannelActivity());
                Assert.assertNotNull(mockObjects.getBotActivity());

                // If the activity is bounced back to the bot we will get a GUID and not the mocked resourceId.
                Assert.assertNotEquals("resourceId", response.getId());

                // Assert the activity sent back to the bot.
                Assert.assertEquals(activityType, mockObjects.getBotActivity().getType());
                Assert.assertEquals(replyToId, mockObjects.getBotActivity().getReplyToId());
            }
        }
    }

    @Test
    public void testCommandActivities() {
        List<String[]> theoryCases = new ArrayList<>();
        theoryCases.add(new String[]{ActivityTypes.COMMAND, "application/myApplicationCommand", null});
        theoryCases.add(new String[]{ActivityTypes.COMMAND, "application/myApplicationCommand", "replyToId"});
        theoryCases.add(new String[]{ActivityTypes.COMMAND, "other/myBotCommand", null});
        theoryCases.add(new String[]{ActivityTypes.COMMAND, "other/myBotCommand", "replyToId"});
        theoryCases.add(new String[]{ActivityTypes.COMMAND_RESULT, "application/myApplicationCommandResult", null});
        theoryCases.add(new String[]{ActivityTypes.COMMAND_RESULT, "application/myApplicationCommandResult", "replyToId"});
        theoryCases.add(new String[]{ActivityTypes.COMMAND_RESULT, "other/myBotCommand", null});
        theoryCases.add(new String[]{ActivityTypes.COMMAND_RESULT, "other/myBotCommand", "replyToId"});

        for (String[] theoryCase : theoryCases) {
            String commandActivityType = theoryCase[0];
            String name = theoryCase[1];
            String replyToId = theoryCase[2];

            // Arrange
            CloudSkillHandlerTestMocks mockObjects = new CloudSkillHandlerTestMocks();
            Activity activity = new Activity(commandActivityType);
            activity.setName(name);
            activity.setReplyToId(replyToId);
            String conversationId = mockObjects.createAndApplyConversationId(activity).join();

            // Act
            CloudSkillHandler sut = new CloudSkillHandler(
                mockObjects.getAdapter(),
                mockObjects.getBot(),
                mockObjects.getConversationIdFactory(),
                mockObjects.getAuth());

            ResourceResponse response = replyToId == null
                ? sut.handleSendToConversation(TEST_AUTH_HEADER, conversationId, activity).join()
                : sut.handleReplyToActivity(TEST_AUTH_HEADER, conversationId, replyToId, activity).join();

            // Assert
            // Assert the turnContext
            Assert.assertEquals(
                CallerIdConstants.BOT_TO_BOT_PREFIX.concat(TEST_SKILL_ID),
                mockObjects.getTurnContext().getActivity().getCallerId());
            Assert.assertNotNull(
                mockObjects.getTurnContext().getTurnState().get(CloudSkillHandler.SKILL_CONVERSATION_REFERENCE_KEY));

            if (StringUtils.startsWith(name, "application/")) {
                // Should be sent to the channel and not to the bot.
                Assert.assertNotNull(mockObjects.getChannelActivity());
                Assert.assertNull(mockObjects.getBotActivity());

                // We should get the resourceId returned by the mock.
                Assert.assertEquals("resourceId", response.getId());
            } else {
                // Should be sent to the bot and not to the channel.
                Assert.assertNull(mockObjects.getChannelActivity());
                Assert.assertNotNull(mockObjects.getBotActivity());

                // If the activity is bounced back to the bot we will get a GUID and not the mocked resourceId.
                Assert.assertNotEquals("resourceId", response.getId());
            }
        }
    }

    @Test
    public void testDeleteActivity() {
        // Arrange
        CloudSkillHandlerTestMocks mockObjects = new CloudSkillHandlerTestMocks();
        Activity activity = new Activity(ActivityTypes.MESSAGE);
        String conversationId = mockObjects.createAndApplyConversationId(activity).join();
        String activityToDelete = UUID.randomUUID().toString();

        // Act
        CloudSkillHandler sut = new CloudSkillHandler(
            mockObjects.getAdapter(),
            mockObjects.getBot(),
            mockObjects.getConversationIdFactory(),
            mockObjects.getAuth());
        sut.handleDeleteActivity(TEST_AUTH_HEADER, conversationId, activityToDelete).join();

        // Assert
        Assert.assertNotNull(mockObjects.getTurnContext().getTurnState().get(CloudSkillHandler.SKILL_CONVERSATION_REFERENCE_KEY));
        Assert.assertEquals(activityToDelete, mockObjects.getActivityIdToDelete());
    }

    @Test
    public void testUpdateActivity() {
        // Arrange
        CloudSkillHandlerTestMocks mockObjects = new CloudSkillHandlerTestMocks();
        Activity activity = new Activity(ActivityTypes.MESSAGE);
        activity.setText(String.format("TestUpdate %s.", LocalDateTime.now()));
        String conversationId = mockObjects.createAndApplyConversationId(activity).join();
        String activityToUpdate = UUID.randomUUID().toString();

        // Act
        CloudSkillHandler sut = new CloudSkillHandler(
            mockObjects.getAdapter(),
            mockObjects.getBot(),
            mockObjects.getConversationIdFactory(),
            mockObjects.getAuth());
        ResourceResponse response = sut.handleUpdateActivity(TEST_AUTH_HEADER, conversationId, activityToUpdate, activity).join();

        // Assert
        Assert.assertEquals("resourceId", response.getId());
        Assert.assertNotNull(mockObjects.getTurnContext().getTurnState().get(CloudSkillHandler.SKILL_CONVERSATION_REFERENCE_KEY));
        Assert.assertEquals(activityToUpdate, mockObjects.getTurnContext().getActivity().getId());
        Assert.assertEquals(activity.getText(), mockObjects.getUpdateActivity().getText());
    }

    /**
     * Helper class with mocks for adapter, bot and auth needed to instantiate CloudSkillHandler and run tests.
     * This class also captures the turnContext and activities sent back to the bot and the channel so we can run asserts on them.
     */
    private static class CloudSkillHandlerTestMocks {
        private static final String TEST_BOT_ID = UUID.randomUUID().toString().replace("-", "");
        private static final String TEST_BOT_ENDPOINT = "http://testbot.com/api/messages";
        private static final String TEST_SKILL_ENDPOINT = "http://testskill.com/api/messages";

        private final SkillConversationIdFactoryBase conversationIdFactory;
        private final BotAdapter adapter;
        private final BotFrameworkAuthentication auth;
        private final Bot bot;
        private TurnContext turnContext;
        private Activity channelActivity;
        private Activity botActivity;
        private Activity updateActivity;
        private String activityToDelete;

        public CloudSkillHandlerTestMocks() {
            adapter = createMockAdapter();
            auth = createMockBotFrameworkAuthentication();
            bot = createMockBot();
            conversationIdFactory = new TestSkillConversationIdFactory();
        }

        public SkillConversationIdFactoryBase getConversationIdFactory() {
            return conversationIdFactory;
        }

        public BotAdapter getAdapter() {
            return adapter;
        }

        public BotFrameworkAuthentication getAuth() {
            return auth;
        }

        public Bot getBot() {
            return bot;
        }

        // Gets the TurnContext created to call the bot.
        public TurnContext getTurnContext() {
            return turnContext;
        }

        /**
         * Gets the Activity sent to the channel.
         * @return
         */
        public Activity getChannelActivity() {
            return channelActivity;
        }

        /**
         * Gets the Activity sent to the Bot.
         * @return
         */
        public Activity getBotActivity() {
            return botActivity;
        }

        /**
         * Gets the update activity.
         * @return
         */
        public Activity getUpdateActivity() {
            return updateActivity;
        }

        /**
         * Gets the Activity sent to the Bot.
         * @return
         */
        public String getActivityIdToDelete() {
            return activityToDelete;
        }

        public CompletableFuture<String> createAndApplyConversationId(Activity activity) {
            ConversationReference conversationReference = new ConversationReference();
            ConversationAccount conversationAccount = new ConversationAccount();
            conversationAccount.setId(TEST_BOT_ID);
            conversationReference.setConversation(conversationAccount);
            conversationReference.setServiceUrl(TEST_BOT_ENDPOINT);

            activity.applyConversationReference(conversationReference);

            BotFrameworkSkill skill = new BotFrameworkSkill();
            skill.setAppId(TEST_SKILL_ID);
            skill.setId("skill");

            try {
                skill.setSkillEndpoint(new URI(TEST_SKILL_ENDPOINT));
            }
            catch (URISyntaxException e) {
            }

            SkillConversationIdFactoryOptions options = new SkillConversationIdFactoryOptions();
            options.setFromBotOAuthScope(TEST_BOT_ID);
            options.setFromBotId(TEST_BOT_ID);
            options.setActivity(activity);
            options.setBotFrameworkSkill(skill);

            return getConversationIdFactory().createSkillConversationId(options);
        }

        private BotAdapter createMockAdapter() {
            BotAdapter adapter = Mockito.mock(BotAdapter.class);

            // Mock the adapter ContinueConversationAsync method
            // This code block catches and executes the custom bot callback created by the service handler.
            Mockito.when(
                adapter.continueConversation(
                    Mockito.any(ClaimsIdentity.class),
                    Mockito.any(ConversationReference.class),
                    Mockito.anyString(),
                    Mockito.any(BotCallbackHandler.class))
            ).thenAnswer(
                (Answer<Void>) invocation -> {
                    ConversationReference conv = invocation.getArgument(1);
                    TurnContext turnContext = new TurnContextImpl(adapter, conv.getContinuationActivity());
                    BotCallbackHandler callback = invocation.getArgument(3);
                    callback.invoke(turnContext);
                    return null;
                }
            );

            // Mock the adapter SendActivitiesAsync method
            // (this for the cases where activity is sent back to the parent or channel)
            Mockito.when(
                adapter.sendActivities(
                    Mockito.any(TurnContext.class),
                    Mockito.anyList())
            ).thenAnswer(
                (Answer<Void>) invocation -> {
                    // Capture the activity sent to the channel
                    List<Activity> activities = invocation.getArgument(1);
                    channelActivity = activities.get(0);

                    // Do nothing, we don't want the activities sent to the channel in the tests.
                    return null;
                }
            ).thenReturn(CompletableFuture.completedFuture(new ResourceResponse[]{new ResourceResponse("resourceId")}));

            // Mock the DeleteActivityAsync method
            Mockito.when(
                adapter.deleteActivity(
                    Mockito.any(TurnContext.class),
                    Mockito.any(ConversationReference.class))
            ).thenAnswer(
                (Answer<Void>) invocation -> {
                    // Capture the activity id to delete so we can assert it.
                    ConversationReference conv = invocation.getArgument(1);
                    activityToDelete = conv.getActivityId();
                    return null;
                }
            );

            // Mock the UpdateActivityAsync method
            Mockito.when(
                adapter.updateActivity(
                    Mockito.any(TurnContext.class),
                    Mockito.any(Activity.class))
            ).thenAnswer(
                (Answer<Void>) invocation -> {
                    updateActivity = invocation.getArgument(1);
                    return null;
                }).thenReturn(CompletableFuture.completedFuture(new ResourceResponse("resourceId")));

            return adapter;
        }

        private Bot createMockBot() {
            Bot bot = Mockito.mock(Bot.class);
            Mockito.when(
                bot.onTurn(Mockito.any(TurnContext.class))
            ).thenAnswer(
                (Answer<Void>) invocation -> {
                    TurnContextImpl turnContext = invocation.getArgument(0);
                    botActivity = turnContext.getActivity();
                    return null;
                }
            );

            return bot;
        }

        private BotFrameworkAuthentication createMockBotFrameworkAuthentication() {
            BotFrameworkAuthentication auth = Mockito.mock(BotFrameworkAuthentication.class);

            Mockito.when(
                auth.authenticateChannelRequest(Mockito.any(String.class))
            ).thenAnswer(
                (Answer<ClaimsIdentity>) invocation -> {
                    HashMap<String, String> claims = new HashMap<>();
                    claims.put(AuthenticationConstants.AUDIENCE_CLAIM, TEST_BOT_ID);
                    claims.put(AuthenticationConstants.APPID_CLAIM, TEST_SKILL_ID);
                    claims.put(AuthenticationConstants.SERVICE_URL_CLAIM, TEST_BOT_ENDPOINT);

                    return new ClaimsIdentity("anonymous", claims);
                }
            );
            return auth;
        }
    }

    private static class TestSkillConversationIdFactory extends SkillConversationIdFactoryBase {
        private final ConcurrentHashMap<String, String> conversationRefs = new ConcurrentHashMap<>();

        public CompletableFuture<String> createSkillConversationId(SkillConversationIdFactoryOptions options) {
            SkillConversationReference skillConversationReference = new SkillConversationReference();
            skillConversationReference.setConversationReference(options.getActivity().getConversationReference());
            skillConversationReference.setOAuthScope(options.getFromBotOAuthScope());

            String key =
                String.format(
                    "%s-%s-%s-%s-skillconvo",
                    options.getFromBotId(),
                    options.getBotFrameworkSkill().getAppId(),
                    skillConversationReference.getConversationReference().getConversation().getId(),
                    skillConversationReference.getConversationReference().getChannelId());

            JacksonAdapter jacksonAdapter = new JacksonAdapter();
            try {
                conversationRefs.putIfAbsent(key, jacksonAdapter.serialize(skillConversationReference));
            }
            catch (IOException e) {
            }
            return CompletableFuture.completedFuture(key);
        }

        @Override
        public CompletableFuture<SkillConversationReference> getSkillConversationReference(String skillConversationId) {
            SkillConversationReference conversationReference = null;
            try {
                JacksonAdapter jacksonAdapter = new JacksonAdapter();
                conversationReference = jacksonAdapter.deserialize(
                    conversationRefs.get(skillConversationId),
                    SkillConversationReference.class);
            }
            catch (IOException e) {
            }

            return CompletableFuture.completedFuture(conversationReference);
        }

        @Override
        public CompletableFuture<Void> deleteConversationReference(String skillConversationId) {
            conversationRefs.remove(skillConversationId);
            return CompletableFuture.completedFuture(null);
        }
    }
}