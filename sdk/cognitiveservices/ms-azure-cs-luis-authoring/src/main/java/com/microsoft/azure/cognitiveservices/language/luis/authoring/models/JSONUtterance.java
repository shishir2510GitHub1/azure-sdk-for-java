/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.language.luis.authoring.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Exported Model - Utterance that was used to train the model.
 */
public class JSONUtterance {
    /**
     * The utterance.
     */
    @JsonProperty(value = "text")
    private String text;

    /**
     * The matched intent.
     */
    @JsonProperty(value = "intent")
    private String intent;

    /**
     * The matched entities.
     */
    @JsonProperty(value = "entities")
    private List<JSONEntity> entities;

    /**
     * Get the text value.
     *
     * @return the text value
     */
    public String text() {
        return this.text;
    }

    /**
     * Set the text value.
     *
     * @param text the text value to set
     * @return the JSONUtterance object itself.
     */
    public JSONUtterance withText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Get the intent value.
     *
     * @return the intent value
     */
    public String intent() {
        return this.intent;
    }

    /**
     * Set the intent value.
     *
     * @param intent the intent value to set
     * @return the JSONUtterance object itself.
     */
    public JSONUtterance withIntent(String intent) {
        this.intent = intent;
        return this;
    }

    /**
     * Get the entities value.
     *
     * @return the entities value
     */
    public List<JSONEntity> entities() {
        return this.entities;
    }

    /**
     * Set the entities value.
     *
     * @param entities the entities value to set
     * @return the JSONUtterance object itself.
     */
    public JSONUtterance withEntities(List<JSONEntity> entities) {
        this.entities = entities;
        return this;
    }

}
