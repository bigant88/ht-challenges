package com.hectre.ez

import android.app.Application
import com.hectre.ez.data.repository.IPackagesRepository
import com.hectre.ez.data.repository.PackagesRepository
import timber.log.Timber

class EzApplication : Application() {

    val packagesRepository: IPackagesRepository
        get() = ServiceLocator.providePackagesRepository(this)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

    }
}