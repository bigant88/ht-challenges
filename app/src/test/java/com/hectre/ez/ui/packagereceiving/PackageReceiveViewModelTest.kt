package com.hectre.ez.ui.packagereceiving

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hectre.ez.data.domainmodels.PackageModel
import com.hectre.ez.data.repository.FakeTestPackageRepository
import com.hectre.ez.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Rule

@ExperimentalCoroutinesApi
class PackageReceiveViewModelTest {

    //subject under test
    private lateinit var viewModel: PackageReceiveViewModel

    //use a fake repository to be injected into the view model
    private lateinit var packageRepository: FakeTestPackageRepository

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        packageRepository = FakeTestPackageRepository()
        viewModel = PackageReceiveViewModel(packagesRepository = packageRepository )

    }

    @Test
    fun gethectrePackageList() = runBlockingTest {
        val package1 =
            PackageModel("id1", 1, "alias1", 100000, "0987654321", " Mai Phuong Thuy 1", "Ha Noi", 1, listOf())
        val package2 =
            PackageModel("id2", 1, "alias2", 100000, "0987654322", " Mai Phuong Thuy 2", "Ha Noi", 1, listOf())
        packageRepository.savePackage(package1)
        packageRepository.savePackage(package2)

        //when get packages from repo
        viewModel.getReceivePackagesFromRepo()
        
        //then 
        assertThat(viewModel.receivePackages.getOrAwaitValue(), not(nullValue()))
        assertThat(viewModel.receivePackages.getOrAwaitValue().size, `is`(2))
    }

    @After
    fun tearDown() {

    }
}