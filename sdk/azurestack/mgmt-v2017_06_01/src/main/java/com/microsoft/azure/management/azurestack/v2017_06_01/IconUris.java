/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.azurestack.v2017_06_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Links to product icons.
 */
public class IconUris {
    /**
     * URI to large icon.
     */
    @JsonProperty(value = "large")
    private String large;

    /**
     * URI to wide icon.
     */
    @JsonProperty(value = "wide")
    private String wide;

    /**
     * URI to medium icon.
     */
    @JsonProperty(value = "medium")
    private String medium;

    /**
     * URI to small icon.
     */
    @JsonProperty(value = "small")
    private String small;

    /**
     * URI to hero icon.
     */
    @JsonProperty(value = "hero")
    private String hero;

    /**
     * Get uRI to large icon.
     *
     * @return the large value
     */
    public String large() {
        return this.large;
    }

    /**
     * Set uRI to large icon.
     *
     * @param large the large value to set
     * @return the IconUris object itself.
     */
    public IconUris withLarge(String large) {
        this.large = large;
        return this;
    }

    /**
     * Get uRI to wide icon.
     *
     * @return the wide value
     */
    public String wide() {
        return this.wide;
    }

    /**
     * Set uRI to wide icon.
     *
     * @param wide the wide value to set
     * @return the IconUris object itself.
     */
    public IconUris withWide(String wide) {
        this.wide = wide;
        return this;
    }

    /**
     * Get uRI to medium icon.
     *
     * @return the medium value
     */
    public String medium() {
        return this.medium;
    }

    /**
     * Set uRI to medium icon.
     *
     * @param medium the medium value to set
     * @return the IconUris object itself.
     */
    public IconUris withMedium(String medium) {
        this.medium = medium;
        return this;
    }

    /**
     * Get uRI to small icon.
     *
     * @return the small value
     */
    public String small() {
        return this.small;
    }

    /**
     * Set uRI to small icon.
     *
     * @param small the small value to set
     * @return the IconUris object itself.
     */
    public IconUris withSmall(String small) {
        this.small = small;
        return this;
    }

    /**
     * Get uRI to hero icon.
     *
     * @return the hero value
     */
    public String hero() {
        return this.hero;
    }

    /**
     * Set uRI to hero icon.
     *
     * @param hero the hero value to set
     * @return the IconUris object itself.
     */
    public IconUris withHero(String hero) {
        this.hero = hero;
        return this;
    }

}
