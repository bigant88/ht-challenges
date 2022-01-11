package com.hectre.ez.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hectre.ez.data.repository.IPackagesRepository

class TaskViewModelFactory(private val packagesRepository: IPackagesRepository) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(packagesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
