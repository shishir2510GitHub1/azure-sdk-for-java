/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.recoveryservices.backup.v2017_07_01;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.microsoft.azure.management.recoveryservices.backup.v2017_07_01.implementation.ProtectedItemInner;

/**
 * Base class for backup items.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "protectedItemType")
@JsonTypeName("GenericProtectedItem")
public class GenericProtectedItem extends ProtectedItemInner {
    /**
     * Friendly name of the container.
     */
    @JsonProperty(value = "friendlyName")
    private String friendlyName;

    /**
     * Indicates consistency of policy object and policy applied to this backup
     * item.
     */
    @JsonProperty(value = "policyState")
    private String policyState;

    /**
     * Backup state of this backup item. Possible values include: 'Invalid',
     * 'IRPending', 'Protected', 'ProtectionError', 'ProtectionStopped',
     * 'ProtectionPaused'.
     */
    @JsonProperty(value = "protectionState")
    private ProtectionState protectionState;

    /**
     * Data Plane Service ID of the protected item.
     */
    @JsonProperty(value = "protectedItemId")
    private Long protectedItemId;

    /**
     * Loosely coupled (type, value) associations (example - parent of a
     * protected item).
     */
    @JsonProperty(value = "sourceAssociations")
    private Map<String, String> sourceAssociations;

    /**
     * Name of this backup item's fabric.
     */
    @JsonProperty(value = "fabricName")
    private String fabricName;

    /**
     * Get friendly name of the container.
     *
     * @return the friendlyName value
     */
    public String friendlyName() {
        return this.friendlyName;
    }

    /**
     * Set friendly name of the container.
     *
     * @param friendlyName the friendlyName value to set
     * @return the GenericProtectedItem object itself.
     */
    public GenericProtectedItem withFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Get indicates consistency of policy object and policy applied to this backup item.
     *
     * @return the policyState value
     */
    public String policyState() {
        return this.policyState;
    }

    /**
     * Set indicates consistency of policy object and policy applied to this backup item.
     *
     * @param policyState the policyState value to set
     * @return the GenericProtectedItem object itself.
     */
    public GenericProtectedItem withPolicyState(String policyState) {
        this.policyState = policyState;
        return this;
    }

    /**
     * Get backup state of this backup item. Possible values include: 'Invalid', 'IRPending', 'Protected', 'ProtectionError', 'ProtectionStopped', 'ProtectionPaused'.
     *
     * @return the protectionState value
     */
    public ProtectionState protectionState() {
        return this.protectionState;
    }

    /**
     * Set backup state of this backup item. Possible values include: 'Invalid', 'IRPending', 'Protected', 'ProtectionError', 'ProtectionStopped', 'ProtectionPaused'.
     *
     * @param protectionState the protectionState value to set
     * @return the GenericProtectedItem object itself.
     */
    public GenericProtectedItem withProtectionState(ProtectionState protectionState) {
        this.protectionState = protectionState;
        return this;
    }

    /**
     * Get data Plane Service ID of the protected item.
     *
     * @return the protectedItemId value
     */
    public Long protectedItemId() {
        return this.protectedItemId;
    }

    /**
     * Set data Plane Service ID of the protected item.
     *
     * @param protectedItemId the protectedItemId value to set
     * @return the GenericProtectedItem object itself.
     */
    public GenericProtectedItem withProtectedItemId(Long protectedItemId) {
        this.protectedItemId = protectedItemId;
        return this;
    }

    /**
     * Get loosely coupled (type, value) associations (example - parent of a protected item).
     *
     * @return the sourceAssociations value
     */
    public Map<String, String> sourceAssociations() {
        return this.sourceAssociations;
    }

    /**
     * Set loosely coupled (type, value) associations (example - parent of a protected item).
     *
     * @param sourceAssociations the sourceAssociations value to set
     * @return the GenericProtectedItem object itself.
     */
    public GenericProtectedItem withSourceAssociations(Map<String, String> sourceAssociations) {
        this.sourceAssociations = sourceAssociations;
        return this;
    }

    /**
     * Get name of this backup item's fabric.
     *
     * @return the fabricName value
     */
    public String fabricName() {
        return this.fabricName;
    }

    /**
     * Set name of this backup item's fabric.
     *
     * @param fabricName the fabricName value to set
     * @return the GenericProtectedItem object itself.
     */
    public GenericProtectedItem withFabricName(String fabricName) {
        this.fabricName = fabricName;
        return this;
    }

}