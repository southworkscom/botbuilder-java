// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.bot.connector.authentication;

import org.apache.commons.lang3.StringUtils;

/**
 * A factory for {@link BotFrameworkAuthentication}
 * which encapsulate the environment specific Bot Framework Protocol auth code.
 */
public final class BotFrameworkAuthenticationFactory {

    /**
     * Creates the a {@link BotFrameworkAuthentication} instance for anonymous testing scenarios.
     * @return A new {@link BotFrameworkAuthentication} instance.
     */
    public static BotFrameworkAuthentication create() {
        return create(
            null,
            false,
            null,
            null,
            null,
            null,
            null,
            null,
            new PasswordServiceClientCredentialFactory(),
            new AuthenticationConfiguration());
    }

    public static BotFrameworkAuthentication create(
        String channelService,
        Boolean validateAuthority,
        String toChannelFromBotLoginUrl,
        String toChannelFromBotOAuthScope,
        String toBotFromChannelTokenIssuer,
        String oAuthUrl,
        String toBotFromChannelOpenIdMetadataUrl,
        String toBotFromEmulatorOpenIdMetadataUrl,
        String callerId,
        ServiceClientCredentialsFactory credentialFactory,
        AuthenticationConfiguration authConfiguration
    ) {
        if (StringUtils.isNotBlank(toChannelFromBotLoginUrl) ||
            StringUtils.isNotBlank(toChannelFromBotOAuthScope) ||
            StringUtils.isNotBlank(toBotFromChannelTokenIssuer) ||
            StringUtils.isNotBlank(oAuthUrl) ||
            StringUtils.isNotBlank(toBotFromChannelOpenIdMetadataUrl) ||
            StringUtils.isNotBlank(toBotFromEmulatorOpenIdMetadataUrl) ||
            StringUtils.isNotBlank(callerId)) {
            // if we have any of the 'parameterized' properties defined we'll assume this is the parameterized code

            return new ParameterizedBotFrameworkAuthentication(
                validateAuthority,
                toChannelFromBotLoginUrl,
                toChannelFromBotOAuthScope,
                toBotFromChannelTokenIssuer,
                oAuthUrl,
                toBotFromChannelOpenIdMetadataUrl,
                toBotFromEmulatorOpenIdMetadataUrl,
                callerId,
                credentialFactory,
                authConfiguration
            );
        } else {
            // else apply the built in default behavior, which is either the public cloud or the gov cloud
            // depending on whether we have a channelService value present

            if (StringUtils.isBlank(channelService)) {
                return new PublicCloudBotFrameworkAuthentication(credentialFactory, authConfiguration);
            } else if (channelService == GovernmentAuthenticationConstants.CHANNELSERVICE) {
                return new GovernmentCloudBotFrameworkAuthentication(credentialFactory, authConfiguration);
            } else {
                // The ChannelService value is used an indicator of which built in set of constants to use.
                // If it is not recognized, a full configuration is expected.

                throw new IllegalArgumentException("The provided ChannelService value is not supported.");
            }
        }
    }
}