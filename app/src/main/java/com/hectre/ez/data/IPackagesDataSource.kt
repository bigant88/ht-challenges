package com.hectre.ez.data

import com.hectre.ez.data.domainmodels.PackageModel

interface IPackagesDataSource {
    suspend fun getReceivePackages(): Result<List<PackageModel>>
    suspend fun savePackage(packageModel: PackageModel)
    suspend fun savePackages(packages: List<PackageModel>)
    suspend fun deleteAllPackage()

}