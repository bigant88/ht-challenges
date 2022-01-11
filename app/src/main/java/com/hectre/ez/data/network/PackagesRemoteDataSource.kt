package com.hectre.ez.data.network

import com.hectre.ez.data.Result
import com.hectre.ez.data.domainmodels.PackageModel
import com.hectre.ez.data.IPackagesDataSource
import java.lang.Exception

object PackagesRemoteDataSource : IPackagesDataSource {
    private var accessToken: String =
        ""

    override suspend fun getReceivePackages(): Result<List<PackageModel>> {
        return try {
            val response = AppNetwork.appService.getReceivePackages()
            Result.Success(response.data.asPackageModels())
        }catch (e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun savePackage(packageModel: PackageModel) {
        //TODO
    }

    override suspend fun savePackages(packages: List<PackageModel>) {
        //TODO
    }

    override suspend fun deleteAllPackage() {
        //TODO
    }

}