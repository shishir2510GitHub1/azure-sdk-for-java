/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.recoveryservices.siterecovery.v2018_01_10;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Encryption details for the fabric.
 */
public class EncryptionDetails {
    /**
     * The key encryption key state for the Vmm.
     */
    @JsonProperty(value = "kekState")
    private String kekState;

    /**
     * The key encryption key certificate thumbprint.
     */
    @JsonProperty(value = "kekCertThumbprint")
    private String kekCertThumbprint;

    /**
     * The key encryption key certificate expiry date.
     */
    @JsonProperty(value = "kekCertExpiryDate")
    private DateTime kekCertExpiryDate;

    /**
     * Get the key encryption key state for the Vmm.
     *
     * @return the kekState value
     */
    public String kekState() {
        return this.kekState;
    }

    /**
     * Set the key encryption key state for the Vmm.
     *
     * @param kekState the kekState value to set
     * @return the EncryptionDetails object itself.
     */
    public EncryptionDetails withKekState(String kekState) {
        this.kekState = kekState;
        return this;
    }

    /**
     * Get the key encryption key certificate thumbprint.
     *
     * @return the kekCertThumbprint value
     */
    public String kekCertThumbprint() {
        return this.kekCertThumbprint;
    }

    /**
     * Set the key encryption key certificate thumbprint.
     *
     * @param kekCertThumbprint the kekCertThumbprint value to set
     * @return the EncryptionDetails object itself.
     */
    public EncryptionDetails withKekCertThumbprint(String kekCertThumbprint) {
        this.kekCertThumbprint = kekCertThumbprint;
        return this;
    }

    /**
     * Get the key encryption key certificate expiry date.
     *
     * @return the kekCertExpiryDate value
     */
    public DateTime kekCertExpiryDate() {
        return this.kekCertExpiryDate;
    }

    /**
     * Set the key encryption key certificate expiry date.
     *
     * @param kekCertExpiryDate the kekCertExpiryDate value to set
     * @return the EncryptionDetails object itself.
     */
    public EncryptionDetails withKekCertExpiryDate(DateTime kekCertExpiryDate) {
        this.kekCertExpiryDate = kekCertExpiryDate;
        return this;
    }

}
