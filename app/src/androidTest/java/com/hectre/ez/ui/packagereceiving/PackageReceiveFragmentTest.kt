package com.hectre.ez.ui.packagereceiving

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.hectre.ez.R
import com.hectre.ez.ServiceLocator
import com.hectre.ez.data.domainmodels.PackageModel
import com.hectre.ez.data.repository.FakeAndroidTestPackagesRepository
import com.hectre.ez.data.repository.IPackagesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PackageReceiveFragmentTest {
    private lateinit var repository: IPackagesRepository

    private val package2 =
        PackageModel(
            "id2",
            1,
            "alias2",
            100000,
            "0987654322",
            " Mai Phuong Thuy 2",
            "Ha Noi",
            1,
            listOf()
        )
    private val package3 =
        PackageModel(
            "id3",
            1,
            "alias3",
            100000,
            "0987654323",
            " Mai Phuong Thuy 3",
            "Ha Noi",
            1,
            listOf()
        )
    private val package4 =
        PackageModel(
            "id4",
            1,
            "alias4",
            100000,
            "0987654324",
            " Mai Phuong Thuy 4",
            "Ha Noi",
            1,
            listOf()
        )
    private val remotePackages = listOf(package4, package2, package3).sortedBy { it.id }

    @Before
    fun setUp() {
        repository = FakeAndroidTestPackagesRepository()
        ServiceLocator.packagesRepository = repository
    }

    @After
    fun cleanupDb() = runBlockingTest {
        ServiceLocator.resetRepository()
    }

    //Suspend function 'savePackage' should be called only from a coroutine or another suspend function --> have to use runBlockingTest
    @Test
    fun displayListPackage() = runBlockingTest {
        // Given - on home screen
        // When - launch fragment with data
        repository.savePackages(remotePackages)
        val scenario = launchFragmentInContainer<PackageReceiveFragment>(Bundle(), R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
        // Then - check data displayed
        onView(withId(R.id.packages_receive_recycler_view))
            .check(matches(isDisplayed()))


    }

}
