package com.hectre.ez.data.repository

import com.hectre.ez.data.Result
import com.hectre.ez.data.domainmodels.PackageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PackagesRepositoryTest {

    private val package1 =
        PackageModel("id1", 1, "alias1", 100000, "0987654321", " Mai Phuong Thuy 1", "Ha Noi", 1, listOf())
    private val package2 =
        PackageModel("id2", 1, "alias2", 100000, "0987654322", " Mai Phuong Thuy 2", "Ha Noi", 1, listOf())
    private val package3 =
        PackageModel("id3", 1, "alias3", 100000, "0987654323", " Mai Phuong Thuy 3", "Ha Noi", 1, listOf())
    private val package4 =
        PackageModel("id4", 1, "alias4", 100000, "0987654324", " Mai Phuong Thuy 4", "Ha Noi", 1, listOf())
    private val remotePackages = listOf(package4, package2, package3).sortedBy { it.id }
    private val localPackages = listOf(package1, package3).sortedBy { it.id }

    private lateinit var packageRemoteDataSource: FakeDataSource
    private lateinit var packageLocalDataSource: FakeDataSource

    //class to test
    private lateinit var packagesRepository: PackagesRepository

    @Before
    fun setUp() {
        packageRemoteDataSource = FakeDataSource((remotePackages.toMutableList()))
        packageLocalDataSource = FakeDataSource(localPackages.toMutableList())
        //get a reference of repository
        packagesRepository = PackagesRepository(
            packageLocalDataSource,
            packageRemoteDataSource,
            Dispatchers.Unconfined
        )
        // // TODO Dispatchers.Unconfined should be replaced with Dispatchers.Main
    }

    @Test
    fun getReceivePackages_allFromRemote() = runBlockingTest {
        //when packages are requested from remote
        val packages = packagesRepository.getReceivePackages(isFromRemote = true) as Result.Success

        //then check packages is equal to remote data
        assertThat(packages.data, IsEqual(remotePackages))
    }

    @Test
    fun getReceivePackages_allFromLocal() = runBlockingTest {
        //when packages are requested from remote
        val packages = packagesRepository.getReceivePackages(isFromRemote = false) as Result.Success

        //then check packages is equal to remote data
        assertThat(packages.data, IsEqual(localPackages))
    }

    @Test
    fun savePackage_existingPackage() = runBlockingTest {
        val oldPackages = packagesRepository.getReceivePackages(false) as Result.Success
        //when save an existing package
        packagesRepository.savePackage(package1)
        val newPackages = packagesRepository.getReceivePackages(false) as Result.Success

        //then check the packages size is same with previous
        assertThat(oldPackages.data.size, IsEqual(2))
    }

    @Test
    fun savePackage_newPackage() = runBlockingTest {
        val oldPackages = packagesRepository.getReceivePackages(false) as Result.Success
        //when save an existing package
        packagesRepository.savePackage(package2)
        val newPackages = packagesRepository.getReceivePackages(false) as Result.Success

        //then check the packages size is same with previous
        assertThat(newPackages.data.size , IsEqual(3) )
    }

}