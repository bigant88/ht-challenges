/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hectre.ez.data.repository

import com.hectre.ez.data.IPackagesDataSource
import com.hectre.ez.data.Result
import com.hectre.ez.data.domainmodels.PackageModel
import com.hectre.ez.data.domainmodels.asDbPackageModels
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Repository for fetching devbyte videos from the network and storing them on disk
 */
class PackagesRepository(
    private val packagesLocalDataSource: IPackagesDataSource,
    private val packagesRemoteDataSource: IPackagesDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPackagesRepository {

    override suspend fun getReceivePackages(isFromRemote: Boolean): Result<List<PackageModel>> {
        if (isFromRemote) {
            try {
                getReceivePackagesFromRemoteDataSource()
            } catch (ex: Exception) {
                return Result.Error(ex)
            }
        }
        return packagesLocalDataSource.getReceivePackages()
    }

    override suspend fun savePackage(packageModel: PackageModel) {
        packagesLocalDataSource.savePackage(packageModel)
    }

    override suspend fun savePackages(packages: List<PackageModel>) {
        packagesLocalDataSource.savePackages(packages)
    }

    private suspend fun getReceivePackagesFromRemoteDataSource() {
        val remotePackageModels = packagesRemoteDataSource.getReceivePackages()
        if (remotePackageModels is Result.Success) {
            packagesLocalDataSource.deleteAllPackage()
            packagesLocalDataSource.savePackages(remotePackageModels.data)
        } else if (remotePackageModels is Result.Error) {
            throw remotePackageModels.exception
        }

    }

}
