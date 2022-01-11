package com.hectre.ez.data.repository

import androidx.lifecycle.MutableLiveData
import com.hectre.ez.data.Result
import com.hectre.ez.data.domainmodels.PackageModel
import kotlinx.coroutines.runBlocking

class FakeTestPackageRepository: IPackagesRepository{

    var packagesServiceData: LinkedHashMap<String, PackageModel> = LinkedHashMap()
    private val observableTasks = MutableLiveData<Result<List<PackageModel>>>()

    override suspend fun getReceivePackages(isFromRemote: Boolean): Result<List<PackageModel>> {
        return Result.Success(packagesServiceData.values.toList())
    }

    override suspend fun savePackage(packageModel: PackageModel) {
        packagesServiceData[packageModel.id] = packageModel
    }

    override suspend fun savePackages(packages: List<PackageModel>) {
        for (packageModel in packages){
            packagesServiceData[packageModel.id] = packageModel
        }
    }

}