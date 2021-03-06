/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.storage.v2019_06_01;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;

/**
 * The properties of an ImmutabilityPolicy of a blob container.
 */
@JsonFlatten
public class ImmutabilityPolicyProperties {
    /**
     * The immutability period for the blobs in the container since the policy
     * creation, in days.
     */
    @JsonProperty(value = "properties.immutabilityPeriodSinceCreationInDays", required = true)
    private int immutabilityPeriodSinceCreationInDays;

    /**
     * The ImmutabilityPolicy state of a blob container, possible values
     * include: Locked and Unlocked. Possible values include: 'Locked',
     * 'Unlocked'.
     */
    @JsonProperty(value = "properties.state", access = JsonProperty.Access.WRITE_ONLY)
    private ImmutabilityPolicyState state;

    /**
     * ImmutabilityPolicy Etag.
     */
    @JsonProperty(value = "etag", access = JsonProperty.Access.WRITE_ONLY)
    private String etag;

    /**
     * The ImmutabilityPolicy update history of the blob container.
     */
    @JsonProperty(value = "updateHistory", access = JsonProperty.Access.WRITE_ONLY)
    private List<UpdateHistoryProperty> updateHistory;

    /**
     * Get the immutability period for the blobs in the container since the policy creation, in days.
     *
     * @return the immutabilityPeriodSinceCreationInDays value
     */
    public int immutabilityPeriodSinceCreationInDays() {
        return this.immutabilityPeriodSinceCreationInDays;
    }

    /**
     * Set the immutability period for the blobs in the container since the policy creation, in days.
     *
     * @param immutabilityPeriodSinceCreationInDays the immutabilityPeriodSinceCreationInDays value to set
     * @return the ImmutabilityPolicyProperties object itself.
     */
    public ImmutabilityPolicyProperties withImmutabilityPeriodSinceCreationInDays(int immutabilityPeriodSinceCreationInDays) {
        this.immutabilityPeriodSinceCreationInDays = immutabilityPeriodSinceCreationInDays;
        return this;
    }

    /**
     * Get the ImmutabilityPolicy state of a blob container, possible values include: Locked and Unlocked. Possible values include: 'Locked', 'Unlocked'.
     *
     * @return the state value
     */
    public ImmutabilityPolicyState state() {
        return this.state;
    }

    /**
     * Get immutabilityPolicy Etag.
     *
     * @return the etag value
     */
    public String etag() {
        return this.etag;
    }

    /**
     * Get the ImmutabilityPolicy update history of the blob container.
     *
     * @return the updateHistory value
     */
    public List<UpdateHistoryProperty> updateHistory() {
        return this.updateHistory;
    }

}
