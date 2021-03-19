// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.bot.applicationinsights;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.EventTelemetry;
import com.microsoft.applicationinsights.telemetry.ExceptionTelemetry;
import com.microsoft.applicationinsights.telemetry.PageViewTelemetry;
import com.microsoft.applicationinsights.telemetry.RemoteDependencyTelemetry;
import com.microsoft.applicationinsights.telemetry.SeverityLevel;
import com.microsoft.applicationinsights.telemetry.TraceTelemetry;
import com.microsoft.bot.builder.BotTelemetryClient;
import com.microsoft.bot.builder.Severity;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A logging client for bot telemetry.
 */
public class BotTelemetryClientImpl implements BotTelemetryClient {

    private final TelemetryClient telemetryClient;

    /**
     * Initializes a new instance of the {@link BotTelemetryClient}.
     *
     * @param withTelemetryClient The telemetry client to forward bot events to.
     */
    public BotTelemetryClientImpl(TelemetryClient withTelemetryClient) {
        if (withTelemetryClient == null) {
            throw new IllegalArgumentException("withTelemetry should be provided");
        }
        this.telemetryClient = withTelemetryClient;
    }

    /**
     * Send information about availability of an application.
     *
     * @param name Availability test name.
     * @param timeStamp The time when the availability was captured.
     * @param duration The time taken for the availability test to run.
     * @param runLocation Name of the location the availability test was run from.
     * @param success True if the availability test ran successfully.
     * @param message Error message on availability test run failure.
     * @param properties Named string values you can use to classify and search for this availability telemetry.
     * @param metrics Additional values associated with this availability telemetry.
     */
    @SuppressWarnings("checkstyle:ParameterNumber")
    @Override
    public void trackAvailability(String name,
                                  OffsetDateTime timeStamp,
                                  Duration duration,
                                  String runLocation,
                                  boolean success,
                                  String message,
                                  Map<String, String> properties,
                                  Map<String, Double> metrics) {
        com.microsoft.applicationinsights.telemetry.Duration durationTelemetry =
            new com.microsoft.applicationinsights.telemetry.Duration(duration.toNanos());
        ConcurrentMap<String, String> concurrentProperties = new ConcurrentHashMap<>(properties);
        ConcurrentMap<String, Double> concurrentMetrics = new ConcurrentHashMap<>(metrics);
        AvailabilityTelemetry telemetry = new AvailabilityTelemetry(
            name,
            durationTelemetry,
            runLocation,
            message,
            success,
            concurrentMetrics,
            concurrentProperties);
        if (properties != null) {
            for (Map.Entry<String, String> pair: properties.entrySet()) {
                telemetry.getProperties().put(pair.getKey(), pair.getValue());
            }
        }

        if (metrics != null) {
            for (Map.Entry<String, Double> pair: metrics.entrySet()) {
                telemetry.getMetrics().put(pair.getKey(), pair.getValue());
            }
        }

        /**
         * TODO : this should be telemetryClient.trackAvailability(telemetry).
         * However, this is not present in TelemetryClient class
         */
        telemetryClient.track(telemetry);
    }

    /**
     * Send information about an external dependency (outgoing call) in the application.
     *
     * @param dependencyTypeName Name of the command initiated with this dependency call. Low cardinality value.
     * Examples are SQL, Azure table, and HTTP.
     * @param target External dependency target.
     * @param dependencyName Name of the command initiated with this dependency call. Low cardinality value.
     * Examples are stored procedure name and URL path template.
     * @param data Command initiated by this dependency call. Examples are SQL statement and HTTP
     * URL's with all query parameters.
     * @param startTime The time when the dependency was called.
     * @param duration The time taken by the external dependency to handle the call.
     * @param resultCode Result code of dependency call execution.
     * @param success True if the dependency call was handled successfully.
     */
    @SuppressWarnings("checkstyle:ParameterNumber")
    @Override
    public void trackDependency(String dependencyTypeName,
                                String target,
                                String dependencyName,
                                String data,
                                OffsetDateTime startTime,
                                Duration duration,
                                String resultCode,
                                boolean success) {
        RemoteDependencyTelemetry telemetry = new RemoteDependencyTelemetry();

        telemetry.setType(dependencyTypeName);
        telemetry.setTarget(target);
        telemetry.setName(dependencyName);
        // TODO : RemoteDependencyTelemetry has the getData as protected, so we can't access
        telemetry.setTimestamp(new Date(startTime.toInstant().toEpochMilli()));
        com.microsoft.applicationinsights.telemetry.Duration durationTelemetry =
            new com.microsoft.applicationinsights.telemetry.Duration(duration.toNanos());
        telemetry.setDuration(durationTelemetry);
        telemetry.setResultCode(resultCode);
        telemetry.setSuccess(success);

        telemetryClient.trackDependency(telemetry);
    }

    /**
     * Logs custom events with extensible named fields.
     *
     * @param eventName A name for the event.
     * @param properties Named string values you can use to search and classify events.
     * @param metrics Measurements associated with this event.
     */
    @Override
    public void trackEvent(String eventName, Map<String, String> properties, Map<String, Double> metrics) {
        EventTelemetry telemetry = new EventTelemetry(eventName);
        if (properties != null) {
            for (Map.Entry<String, String> pair: properties.entrySet()) {
                telemetry.getProperties().put(pair.getKey(), pair.getValue());
            }
        }

        if (metrics != null) {
            for (Map.Entry<String, Double> pair: metrics.entrySet()) {
                telemetry.getMetrics().put(pair.getKey(), pair.getValue());
            }
        }

        telemetryClient.trackEvent(telemetry);
    }

    /**
     * Logs a system exception.
     *
     * @param exception The exception to log.
     * @param properties Named string values you can use to classify and search for this exception.
     * @param metrics Additional values associated with this exception
     */
    @Override
    public void trackException(Exception exception, Map<String, String> properties, Map<String, Double> metrics) {
        ExceptionTelemetry telemetry = new ExceptionTelemetry(exception);
        if (properties != null) {
            for (Map.Entry<String, String> pair: properties.entrySet()) {
                telemetry.getProperties().put(pair.getKey(), pair.getValue());
            }
        }

        if (metrics != null) {
            for (Map.Entry<String, Double> pair: metrics.entrySet()) {
                telemetry.getMetrics().put(pair.getKey(), pair.getValue());
            }
        }

        telemetryClient.trackException(telemetry);
    }

    /**
     * Send a trace message.
     *
     * @param message Message to display.
     * @param severityLevel Trace severity level {@link Severity}.
     * @param properties Named string values you can use to search and classify events.
     */
    @Override
    public void trackTrace(String message, Severity severityLevel, Map<String, String> properties) {
        TraceTelemetry telemetry = new TraceTelemetry(message);
        telemetry.setSeverityLevel(SeverityLevel.values()[severityLevel.ordinal()]);

        if (properties != null) {
            for (Map.Entry<String, String> pair: properties.entrySet()) {
                telemetry.getProperties().put(pair.getKey(), pair.getValue());
            }
        }

        telemetryClient.trackTrace(telemetry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trackDialogView(String dialogName, Map<String, String> properties, Map<String, Double> metrics) {
        if (telemetryClient instanceof BotTelemetryClient) {
            trackPageView(dialogName, properties, metrics);
        } else {
            telemetryClient.trackTrace("Dialog View: " + dialogName, SeverityLevel.Information, properties);
        }
    }

    /**
     * Logs a dialog entry / as an Application Insights page view.
     *
     * @param dialogName The name of the dialog to log the entry / start for.
     * @param properties Named string values you can use to search and classify events.
     * @param metrics Measurements associated with this event.
     */
    public void trackPageView(String dialogName, Map<String, String> properties, Map<String, Double> metrics) {
        PageViewTelemetry telemetry = new PageViewTelemetry(dialogName);

        if (properties != null) {
            for (Map.Entry<String, String> pair: properties.entrySet()) {
                telemetry.getProperties().put(pair.getKey(), pair.getValue());
            }
        }

        if (metrics != null) {
            for (Map.Entry<String, Double> pair: metrics.entrySet()) {
                telemetry.getMetrics().put(pair.getKey(), pair.getValue());
            }
        }

        telemetryClient.trackPageView(telemetry);
    }

    /**
     * Flushes the in-memory buffer and any metrics being pre-aggregated.
     */
    @Override
    public void flush() {
        telemetryClient.flush();
    }
}
