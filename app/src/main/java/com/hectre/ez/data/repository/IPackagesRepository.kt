package com.hectre.ez.data.repository

import com.hectre.ez.data.Result
import com.hectre.ez.data.domainmodels.PackageModel

interface IPackagesRepository {

    suspend fun getReceivePackages(isFromRemote: Boolean = false): Result<List<PackageModel>>
    suspend fun savePackage(packageModel: PackageModel)
    suspend fun savePackages(packages: List<PackageModel>)

}