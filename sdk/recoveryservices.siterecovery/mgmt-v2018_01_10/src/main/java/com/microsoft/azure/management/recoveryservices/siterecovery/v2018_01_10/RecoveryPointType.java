/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.recoveryservices.siterecovery.v2018_01_10;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for RecoveryPointType.
 */
public final class RecoveryPointType extends ExpandableStringEnum<RecoveryPointType> {
    /** Static value LatestTime for RecoveryPointType. */
    public static final RecoveryPointType LATEST_TIME = fromString("LatestTime");

    /** Static value LatestTag for RecoveryPointType. */
    public static final RecoveryPointType LATEST_TAG = fromString("LatestTag");

    /** Static value Custom for RecoveryPointType. */
    public static final RecoveryPointType CUSTOM = fromString("Custom");

    /**
     * Creates or finds a RecoveryPointType from its string representation.
     * @param name a name to look for
     * @return the corresponding RecoveryPointType
     */
    @JsonCreator
    public static RecoveryPointType fromString(String name) {
        return fromString(name, RecoveryPointType.class);
    }

    /**
     * @return known RecoveryPointType values
     */
    public static Collection<RecoveryPointType> values() {
        return values(RecoveryPointType.class);
    }
}
