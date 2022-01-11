package com.hectre.ez.data.local

import com.hectre.ez.data.IPackagesDataSource
import com.hectre.ez.data.Result
import com.hectre.ez.data.domainmodels.PackageModel
import com.hectre.ez.data.domainmodels.asDbPackageModel
import com.hectre.ez.data.domainmodels.asDbPackageModels
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PackagesLocalDataSource internal constructor(
    private val packagesDao: DBPackageDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPackagesDataSource {

    override suspend fun getReceivePackages(): Result<List<PackageModel>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(packagesDao.getAllPackages().asPackageModels())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun savePackage(packageModel: PackageModel) = withContext(ioDispatcher){
        packagesDao.insertOrUpdatePackage(packageModel.asDbPackageModel())
    }

    override suspend fun savePackages(packages: List<PackageModel>) {
        packagesDao.insertAll(packages.asDbPackageModels())
    }

    override suspend fun deleteAllPackage() {
        packagesDao.deleteAll()
    }

}