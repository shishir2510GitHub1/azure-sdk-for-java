/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.mediaservices.v2018_03_30_preview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for StreamingPolicyStreamingProtocol.
 */
public enum StreamingPolicyStreamingProtocol {
    /** HLS protocol. */
    HLS("Hls"),

    /** DASH protocol. */
    DASH("Dash"),

    /** SmoothStreaming protocol. */
    SMOOTH_STREAMING("SmoothStreaming"),

    /** Download protocol. */
    DOWNLOAD("Download");

    /** The actual serialized value for a StreamingPolicyStreamingProtocol instance. */
    private String value;

    StreamingPolicyStreamingProtocol(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a StreamingPolicyStreamingProtocol instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed StreamingPolicyStreamingProtocol object, or null if unable to parse.
     */
    @JsonCreator
    public static StreamingPolicyStreamingProtocol fromString(String value) {
        StreamingPolicyStreamingProtocol[] items = StreamingPolicyStreamingProtocol.values();
        for (StreamingPolicyStreamingProtocol item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
