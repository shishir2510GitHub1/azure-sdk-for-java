/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.network.v2019_09_01.implementation;

import java.util.Map;
import com.microsoft.azure.management.network.v2019_09_01.ConnectionMonitorSource;
import com.microsoft.azure.management.network.v2019_09_01.ConnectionMonitorDestination;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;

/**
 * Parameters that define the operation to create a connection monitor.
 */
@JsonFlatten
public class ConnectionMonitorInner {
    /**
     * Connection monitor location.
     */
    @JsonProperty(value = "location")
    private String location;

    /**
     * Connection monitor tags.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * Describes the source of connection monitor.
     */
    @JsonProperty(value = "properties.source", required = true)
    private ConnectionMonitorSource source;

    /**
     * Describes the destination of connection monitor.
     */
    @JsonProperty(value = "properties.destination", required = true)
    private ConnectionMonitorDestination destination;

    /**
     * Determines if the connection monitor will start automatically once
     * created.
     */
    @JsonProperty(value = "properties.autoStart")
    private Boolean autoStart;

    /**
     * Monitoring interval in seconds.
     */
    @JsonProperty(value = "properties.monitoringIntervalInSeconds")
    private Integer monitoringIntervalInSeconds;

    /**
     * Get connection monitor location.
     *
     * @return the location value
     */
    public String location() {
        return this.location;
    }

    /**
     * Set connection monitor location.
     *
     * @param location the location value to set
     * @return the ConnectionMonitorInner object itself.
     */
    public ConnectionMonitorInner withLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get connection monitor tags.
     *
     * @return the tags value
     */
    public Map<String, String> tags() {
        return this.tags;
    }

    /**
     * Set connection monitor tags.
     *
     * @param tags the tags value to set
     * @return the ConnectionMonitorInner object itself.
     */
    public ConnectionMonitorInner withTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get describes the source of connection monitor.
     *
     * @return the source value
     */
    public ConnectionMonitorSource source() {
        return this.source;
    }

    /**
     * Set describes the source of connection monitor.
     *
     * @param source the source value to set
     * @return the ConnectionMonitorInner object itself.
     */
    public ConnectionMonitorInner withSource(ConnectionMonitorSource source) {
        this.source = source;
        return this;
    }

    /**
     * Get describes the destination of connection monitor.
     *
     * @return the destination value
     */
    public ConnectionMonitorDestination destination() {
        return this.destination;
    }

    /**
     * Set describes the destination of connection monitor.
     *
     * @param destination the destination value to set
     * @return the ConnectionMonitorInner object itself.
     */
    public ConnectionMonitorInner withDestination(ConnectionMonitorDestination destination) {
        this.destination = destination;
        return this;
    }

    /**
     * Get determines if the connection monitor will start automatically once created.
     *
     * @return the autoStart value
     */
    public Boolean autoStart() {
        return this.autoStart;
    }

    /**
     * Set determines if the connection monitor will start automatically once created.
     *
     * @param autoStart the autoStart value to set
     * @return the ConnectionMonitorInner object itself.
     */
    public ConnectionMonitorInner withAutoStart(Boolean autoStart) {
        this.autoStart = autoStart;
        return this;
    }

    /**
     * Get monitoring interval in seconds.
     *
     * @return the monitoringIntervalInSeconds value
     */
    public Integer monitoringIntervalInSeconds() {
        return this.monitoringIntervalInSeconds;
    }

    /**
     * Set monitoring interval in seconds.
     *
     * @param monitoringIntervalInSeconds the monitoringIntervalInSeconds value to set
     * @return the ConnectionMonitorInner object itself.
     */
    public ConnectionMonitorInner withMonitoringIntervalInSeconds(Integer monitoringIntervalInSeconds) {
        this.monitoringIntervalInSeconds = monitoringIntervalInSeconds;
        return this;
    }

}
