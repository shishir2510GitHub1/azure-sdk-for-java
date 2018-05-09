/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.scheduler.v2016_03_01;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.management.scheduler.v2016_03_01.implementation.JobDefinitionInner;
import com.microsoft.azure.arm.model.Indexable;
import com.microsoft.azure.arm.model.Refreshable;
import com.microsoft.azure.arm.model.Updatable;
import com.microsoft.azure.arm.model.Appliable;
import com.microsoft.azure.arm.model.Creatable;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.scheduler.v2016_03_01.implementation.SchedulerManager;

/**
 * Type representing JobDefinition.
 */
public interface JobDefinition extends HasInner<JobDefinitionInner>, Indexable, Refreshable<JobDefinition>, Updatable<JobDefinition.Update>, HasManager<SchedulerManager> {
    /**
     * @return the id value.
     */
    String id();

    /**
     * @return the name value.
     */
    String name();

    /**
     * @return the properties value.
     */
    JobProperties properties();

    /**
     * @return the type value.
     */
    String type();

    /**
     * The entirety of the JobDefinition definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithJobCollection, DefinitionStages.WithCreate {
    }

    /**
     * Grouping of JobDefinition definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of a JobDefinition definition.
         */
        interface Blank extends WithJobCollection {
        }

        /**
         * The stage of the jobdefinition definition allowing to specify JobCollection.
         */
        interface WithJobCollection {
           /**
            * Specifies resourceGroupName, jobCollectionName.
            */
            WithCreate withExistingJobCollection(String resourceGroupName, String jobCollectionName);
        }

        /**
         * The stage of the definition which contains all the minimum required inputs for
         * the resource to be created (via {@link WithCreate#create()}), but also allows
         * for any other optional settings to be specified.
         */
        interface WithCreate extends Creatable<JobDefinition> {
        }
    }
    /**
     * The template for a JobDefinition update operation, containing all the settings that can be modified.
     */
    interface Update extends Appliable<JobDefinition> {
    }

    /**
     * Grouping of JobDefinition update stages.
     */
    interface UpdateStages {
    }
}