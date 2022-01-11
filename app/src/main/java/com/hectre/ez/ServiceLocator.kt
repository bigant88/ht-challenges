package com.hectre.ez

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.hectre.ez.data.local.AppDatabase
import com.hectre.ez.data.local.PackagesLocalDataSource
import com.hectre.ez.data.network.PackagesRemoteDataSource
import com.hectre.ez.data.IPackagesDataSource
import com.hectre.ez.data.repository.IPackagesRepository
import com.hectre.ez.data.repository.PackagesRepository
import kotlinx.coroutines.runBlocking

object ServiceLocator {
    private val lock = Any()

    private var database: AppDatabase? = null

    @Volatile
    var packagesRepository: IPackagesRepository? = null
        @VisibleForTesting set

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            runBlocking {
                PackagesRemoteDataSource.deleteAllPackage()
            }
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            packagesRepository = null
        }
    }

    fun providePackagesRepository(context: Context): IPackagesRepository {
        synchronized(this) {
            return packagesRepository ?: createPackagesRepository(context)
        }
    }

    private fun createPackagesRepository(context: Context): IPackagesRepository {
        val newRepo =
            PackagesRepository(createPackageLocalDataSource(context), PackagesRemoteDataSource)
        packagesRepository = newRepo
        return newRepo
    }

    private fun createPackageLocalDataSource(context: Context): IPackagesDataSource {
        val database = database ?: createDatabase(context)
        return PackagesLocalDataSource(database.dbPackageDao)
    }

    private fun createDatabase(context: Context): AppDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
        database = result
        return result

    }
}