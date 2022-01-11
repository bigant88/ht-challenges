package com.hectre.ez.data.repository

import com.hectre.ez.data.IPackagesDataSource
import com.hectre.ez.data.Result
import com.hectre.ez.data.Result.Error
import com.hectre.ez.data.domainmodels.PackageModel

import java.lang.Exception

class FakeDataSource (var packages: MutableList<PackageModel>? = mutableListOf()):
    IPackagesDataSource {

    override suspend fun getReceivePackages(): Result<List<PackageModel>> {
        packages?.let { return Result.Success(ArrayList(it)) }
        return Error(Exception("packages not found"))
    }

    override suspend fun savePackage(packageModel: PackageModel) {
        packages?.add(packageModel)
    }

    override suspend fun savePackages(packageList: List<PackageModel>) {
        packages?.addAll(packageList)
    }

    override suspend fun deleteAllPackage() {
        packages?.clear()
    }

}