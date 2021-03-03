// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.bot.sample.core;

import com.microsoft.bot.builder.MessageFactory;
import com.microsoft.bot.dialogs.DialogTurnResult;
import com.microsoft.bot.dialogs.WaterfallDialog;
import com.microsoft.bot.dialogs.WaterfallStep;
import com.microsoft.bot.dialogs.WaterfallStepContext;
import com.microsoft.bot.dialogs.prompts.ConfirmPrompt;
import com.microsoft.bot.dialogs.prompts.PromptOptions;
import com.microsoft.bot.dialogs.prompts.TextPrompt;
import com.microsoft.bot.schema.Activity;
import com.microsoft.bot.schema.InputHints;
import com.microsoft.recognizers.datatypes.timex.expression.Constants;
import com.microsoft.recognizers.datatypes.timex.expression.TimexProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 *
 */
public class BookingDialog extends CancelAndHelpDialog {
    private final String destinationStepMsgText = "Where would you like to travel to?";
    private final String originStepMsgText = "Where are you traveling from?";

    /**
     *
     */
    public BookingDialog() {
        super(BookingDialog.class.getName());

        addDialog(new TextPrompt(TextPrompt.class.getName()));
        addDialog(new ConfirmPrompt(ConfirmPrompt.class.getName()));
        addDialog(new DateResolverDialog(null));
        WaterfallStep[] waterfallSteps = {
            this::destinationStep,
            this::originStep,
            this::travelDateStep,
            this::confirmStep,
            this::finalStep
        };
        addDialog(new WaterfallDialog(WaterfallDialog.class.getName(), Arrays.asList(waterfallSteps)));

        // The initial child Dialog to run.
        setInitialDialogId(WaterfallDialog.class.getName());
    }

    /**
     * If a destination city has not been provided, prompt for one.
     */
    private CompletableFuture<DialogTurnResult> destinationStep(WaterfallStepContext stepContext) {
        BookingDetails bookingDetails = (BookingDetails) stepContext.getOptions();

        if (bookingDetails.getDestination() == null) {
            Activity promptMessage =
                MessageFactory.text(destinationStepMsgText, destinationStepMsgText, InputHints.EXPECTING_INPUT);
            return stepContext.prompt(TextPrompt.class.getName(), new PromptOptions() {{
                setPrompt(promptMessage);
            }});
        }

        return stepContext.next(bookingDetails.getDestination());
    }

    /**
     * If an origin city has not been provided, prompt for one.
     */
    private CompletableFuture<DialogTurnResult> originStep(WaterfallStepContext stepContext) {
        BookingDetails bookingDetails = (BookingDetails) stepContext.getOptions();

        bookingDetails.setDestination(stepContext.getResult().toString());

        if (StringUtils.isNotBlank(bookingDetails.getOrigin())) {
            Activity promptMessage =
                MessageFactory.text(originStepMsgText, originStepMsgText, InputHints.EXPECTING_INPUT);
            return stepContext.prompt(TextPrompt.class.getName(), new PromptOptions() {{
                setPrompt(promptMessage);
            }});
        }

        return stepContext.next(bookingDetails.getOrigin());
    }

    /**
     * If a travel date has not been provided, prompt for one.
     * This will use the DATE_RESOLVER_DIALOG.
     */
    private CompletableFuture<DialogTurnResult> travelDateStep(WaterfallStepContext stepContext) {
        BookingDetails bookingDetails = (BookingDetails) stepContext.getOptions();

        bookingDetails.setOrigin(stepContext.getResult().toString());

        if (StringUtils.isNotBlank(bookingDetails.getTravelDate()) || isAmbiguous(bookingDetails.getTravelDate())) {
            return stepContext.beginDialog(DateResolverDialog.class.getName(), bookingDetails.getTravelDate());
        }

        return stepContext.next(bookingDetails.getTravelDate());
    }

    /**
     * Confirm the information the user has provided.
     */
    private CompletableFuture<DialogTurnResult> confirmStep(WaterfallStepContext stepContext) {
        BookingDetails bookingDetails = (BookingDetails) stepContext.getOptions();

        bookingDetails.setTravelDate(stepContext.getResult().toString());

        String messageText =
            String.format("Please confirm, I have you traveling to: %s from: %s on: %s. Is this correct?",
                bookingDetails.getDestination(), bookingDetails.getOrigin(), bookingDetails.getTravelDate());
        Activity promptMessage = MessageFactory.text(messageText, messageText, InputHints.EXPECTING_INPUT);

        return stepContext.prompt(ConfirmPrompt.class.getName(), new PromptOptions() {{
            setPrompt(promptMessage);
        }});
    }

    /**
     * Complete the interaction and end the dialog.
     */
    private CompletableFuture<DialogTurnResult> finalStep(WaterfallStepContext stepContext) {
        if ((Boolean) stepContext.getResult()) {
            BookingDetails bookingDetails = (BookingDetails) stepContext.getOptions();

            return stepContext.endDialog(bookingDetails);
        }

        return stepContext.endDialog(null);
    }

    private static boolean isAmbiguous(String timex) {
        TimexProperty timexProperty = new TimexProperty(timex);
        return !timexProperty.getTypes().contains(Constants.TimexTypes.DEFINITE);
    }
}
