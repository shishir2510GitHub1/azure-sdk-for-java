/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.network.v2018_04_01.implementation;

import java.util.List;
import com.microsoft.azure.SubResource;
import com.microsoft.azure.management.network.v2018_04_01.ProvisioningState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.rest.SkipParentValidation;
import com.microsoft.azure.Resource;

/**
 * VirtualWAN Resource.
 */
@JsonFlatten
@SkipParentValidation
public class VirtualWANInner extends Resource {
    /**
     * Vpn encryption to be disabled or not.
     */
    @JsonProperty(value = "properties.disableVpnEncryption")
    private Boolean disableVpnEncryption;

    /**
     * List of VirtualHubs in the VirtualWAN.
     */
    @JsonProperty(value = "properties.virtualHubs", access = JsonProperty.Access.WRITE_ONLY)
    private List<SubResource> virtualHubs;

    /**
     * The vpnSites property.
     */
    @JsonProperty(value = "properties.vpnSites", access = JsonProperty.Access.WRITE_ONLY)
    private List<SubResource> vpnSites;

    /**
     * The provisioning state of the resource. Possible values include:
     * 'Succeeded', 'Updating', 'Deleting', 'Failed'.
     */
    @JsonProperty(value = "properties.provisioningState")
    private ProvisioningState provisioningState;

    /**
     * Gets a unique read-only string that changes whenever the resource is
     * updated.
     */
    @JsonProperty(value = "etag", access = JsonProperty.Access.WRITE_ONLY)
    private String etag;

    /**
     * Resource ID.
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * Get vpn encryption to be disabled or not.
     *
     * @return the disableVpnEncryption value
     */
    public Boolean disableVpnEncryption() {
        return this.disableVpnEncryption;
    }

    /**
     * Set vpn encryption to be disabled or not.
     *
     * @param disableVpnEncryption the disableVpnEncryption value to set
     * @return the VirtualWANInner object itself.
     */
    public VirtualWANInner withDisableVpnEncryption(Boolean disableVpnEncryption) {
        this.disableVpnEncryption = disableVpnEncryption;
        return this;
    }

    /**
     * Get list of VirtualHubs in the VirtualWAN.
     *
     * @return the virtualHubs value
     */
    public List<SubResource> virtualHubs() {
        return this.virtualHubs;
    }

    /**
     * Get the vpnSites value.
     *
     * @return the vpnSites value
     */
    public List<SubResource> vpnSites() {
        return this.vpnSites;
    }

    /**
     * Get the provisioning state of the resource. Possible values include: 'Succeeded', 'Updating', 'Deleting', 'Failed'.
     *
     * @return the provisioningState value
     */
    public ProvisioningState provisioningState() {
        return this.provisioningState;
    }

    /**
     * Set the provisioning state of the resource. Possible values include: 'Succeeded', 'Updating', 'Deleting', 'Failed'.
     *
     * @param provisioningState the provisioningState value to set
     * @return the VirtualWANInner object itself.
     */
    public VirtualWANInner withProvisioningState(ProvisioningState provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    /**
     * Get gets a unique read-only string that changes whenever the resource is updated.
     *
     * @return the etag value
     */
    public String etag() {
        return this.etag;
    }

    /**
     * Get resource ID.
     *
     * @return the id value
     */
    public String id() {
        return this.id;
    }

    /**
     * Set resource ID.
     *
     * @param id the id value to set
     * @return the VirtualWANInner object itself.
     */
    public VirtualWANInner withId(String id) {
        this.id = id;
        return this;
    }

}
