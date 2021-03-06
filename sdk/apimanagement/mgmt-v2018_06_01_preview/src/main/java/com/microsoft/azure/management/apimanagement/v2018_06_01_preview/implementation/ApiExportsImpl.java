/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * abc
 */

package com.microsoft.azure.management.apimanagement.v2018_06_01_preview.implementation;

import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.ApiExports;
import rx.functions.Func1;
import rx.Observable;
import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.ApiExportResult;
import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.ExportFormat;

class ApiExportsImpl extends WrapperImpl<ApiExportsInner> implements ApiExports {
    private final ApiManagementManager manager;

    ApiExportsImpl(ApiManagementManager manager) {
        super(manager.inner().apiExports());
        this.manager = manager;
    }

    public ApiManagementManager manager() {
        return this.manager;
    }

    @Override
    public Observable<ApiExportResult> getAsync(String resourceGroupName, String serviceName, String apiId, ExportFormat format) {
        ApiExportsInner client = this.inner();
        return client.getAsync(resourceGroupName, serviceName, apiId, format)
        .map(new Func1<ApiExportResultInner, ApiExportResult>() {
            @Override
            public ApiExportResult call(ApiExportResultInner inner) {
                return new ApiExportResultImpl(inner, manager());
            }
        });
    }

}
