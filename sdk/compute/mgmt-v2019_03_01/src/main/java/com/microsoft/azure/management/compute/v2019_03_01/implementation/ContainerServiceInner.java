/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.v2019_03_01.implementation;

import com.microsoft.azure.management.compute.v2019_03_01.ContainerServiceOrchestratorProfile;
import com.microsoft.azure.management.compute.v2019_03_01.ContainerServiceCustomProfile;
import com.microsoft.azure.management.compute.v2019_03_01.ContainerServiceServicePrincipalProfile;
import com.microsoft.azure.management.compute.v2019_03_01.ContainerServiceMasterProfile;
import java.util.List;
import com.microsoft.azure.management.compute.v2019_03_01.ContainerServiceAgentPoolProfile;
import com.microsoft.azure.management.compute.v2019_03_01.ContainerServiceWindowsProfile;
import com.microsoft.azure.management.compute.v2019_03_01.ContainerServiceLinuxProfile;
import com.microsoft.azure.management.compute.v2019_03_01.ContainerServiceDiagnosticsProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.Resource;

/**
 * Container service.
 */
@JsonFlatten
public class ContainerServiceInner extends Resource {
    /**
     * the current deployment or provisioning state, which only appears in the
     * response.
     */
    @JsonProperty(value = "properties.provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private String provisioningState;

    /**
     * Properties of the orchestrator.
     */
    @JsonProperty(value = "properties.orchestratorProfile")
    private ContainerServiceOrchestratorProfile orchestratorProfile;

    /**
     * Properties for custom clusters.
     */
    @JsonProperty(value = "properties.customProfile")
    private ContainerServiceCustomProfile customProfile;

    /**
     * Properties for cluster service principals.
     */
    @JsonProperty(value = "properties.servicePrincipalProfile")
    private ContainerServiceServicePrincipalProfile servicePrincipalProfile;

    /**
     * Properties of master agents.
     */
    @JsonProperty(value = "properties.masterProfile", required = true)
    private ContainerServiceMasterProfile masterProfile;

    /**
     * Properties of the agent pool.
     */
    @JsonProperty(value = "properties.agentPoolProfiles", required = true)
    private List<ContainerServiceAgentPoolProfile> agentPoolProfiles;

    /**
     * Properties of Windows VMs.
     */
    @JsonProperty(value = "properties.windowsProfile")
    private ContainerServiceWindowsProfile windowsProfile;

    /**
     * Properties of Linux VMs.
     */
    @JsonProperty(value = "properties.linuxProfile", required = true)
    private ContainerServiceLinuxProfile linuxProfile;

    /**
     * Properties of the diagnostic agent.
     */
    @JsonProperty(value = "properties.diagnosticsProfile")
    private ContainerServiceDiagnosticsProfile diagnosticsProfile;

    /**
     * Get the current deployment or provisioning state, which only appears in the response.
     *
     * @return the provisioningState value
     */
    public String provisioningState() {
        return this.provisioningState;
    }

    /**
     * Get properties of the orchestrator.
     *
     * @return the orchestratorProfile value
     */
    public ContainerServiceOrchestratorProfile orchestratorProfile() {
        return this.orchestratorProfile;
    }

    /**
     * Set properties of the orchestrator.
     *
     * @param orchestratorProfile the orchestratorProfile value to set
     * @return the ContainerServiceInner object itself.
     */
    public ContainerServiceInner withOrchestratorProfile(ContainerServiceOrchestratorProfile orchestratorProfile) {
        this.orchestratorProfile = orchestratorProfile;
        return this;
    }

    /**
     * Get properties for custom clusters.
     *
     * @return the customProfile value
     */
    public ContainerServiceCustomProfile customProfile() {
        return this.customProfile;
    }

    /**
     * Set properties for custom clusters.
     *
     * @param customProfile the customProfile value to set
     * @return the ContainerServiceInner object itself.
     */
    public ContainerServiceInner withCustomProfile(ContainerServiceCustomProfile customProfile) {
        this.customProfile = customProfile;
        return this;
    }

    /**
     * Get properties for cluster service principals.
     *
     * @return the servicePrincipalProfile value
     */
    public ContainerServiceServicePrincipalProfile servicePrincipalProfile() {
        return this.servicePrincipalProfile;
    }

    /**
     * Set properties for cluster service principals.
     *
     * @param servicePrincipalProfile the servicePrincipalProfile value to set
     * @return the ContainerServiceInner object itself.
     */
    public ContainerServiceInner withServicePrincipalProfile(ContainerServiceServicePrincipalProfile servicePrincipalProfile) {
        this.servicePrincipalProfile = servicePrincipalProfile;
        return this;
    }

    /**
     * Get properties of master agents.
     *
     * @return the masterProfile value
     */
    public ContainerServiceMasterProfile masterProfile() {
        return this.masterProfile;
    }

    /**
     * Set properties of master agents.
     *
     * @param masterProfile the masterProfile value to set
     * @return the ContainerServiceInner object itself.
     */
    public ContainerServiceInner withMasterProfile(ContainerServiceMasterProfile masterProfile) {
        this.masterProfile = masterProfile;
        return this;
    }

    /**
     * Get properties of the agent pool.
     *
     * @return the agentPoolProfiles value
     */
    public List<ContainerServiceAgentPoolProfile> agentPoolProfiles() {
        return this.agentPoolProfiles;
    }

    /**
     * Set properties of the agent pool.
     *
     * @param agentPoolProfiles the agentPoolProfiles value to set
     * @return the ContainerServiceInner object itself.
     */
    public ContainerServiceInner withAgentPoolProfiles(List<ContainerServiceAgentPoolProfile> agentPoolProfiles) {
        this.agentPoolProfiles = agentPoolProfiles;
        return this;
    }

    /**
     * Get properties of Windows VMs.
     *
     * @return the windowsProfile value
     */
    public ContainerServiceWindowsProfile windowsProfile() {
        return this.windowsProfile;
    }

    /**
     * Set properties of Windows VMs.
     *
     * @param windowsProfile the windowsProfile value to set
     * @return the ContainerServiceInner object itself.
     */
    public ContainerServiceInner withWindowsProfile(ContainerServiceWindowsProfile windowsProfile) {
        this.windowsProfile = windowsProfile;
        return this;
    }

    /**
     * Get properties of Linux VMs.
     *
     * @return the linuxProfile value
     */
    public ContainerServiceLinuxProfile linuxProfile() {
        return this.linuxProfile;
    }

    /**
     * Set properties of Linux VMs.
     *
     * @param linuxProfile the linuxProfile value to set
     * @return the ContainerServiceInner object itself.
     */
    public ContainerServiceInner withLinuxProfile(ContainerServiceLinuxProfile linuxProfile) {
        this.linuxProfile = linuxProfile;
        return this;
    }

    /**
     * Get properties of the diagnostic agent.
     *
     * @return the diagnosticsProfile value
     */
    public ContainerServiceDiagnosticsProfile diagnosticsProfile() {
        return this.diagnosticsProfile;
    }

    /**
     * Set properties of the diagnostic agent.
     *
     * @param diagnosticsProfile the diagnosticsProfile value to set
     * @return the ContainerServiceInner object itself.
     */
    public ContainerServiceInner withDiagnosticsProfile(ContainerServiceDiagnosticsProfile diagnosticsProfile) {
        this.diagnosticsProfile = diagnosticsProfile;
        return this;
    }

}
