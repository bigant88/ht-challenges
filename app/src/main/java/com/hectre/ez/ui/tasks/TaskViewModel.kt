package com.hectre.ez.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hectre.ez.data.domainmodels.PackageModel
import com.hectre.ez.data.repository.IPackagesRepository
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

class TaskViewModel(private val packagesRepository: IPackagesRepository) : ViewModel() {
    //
    private val _taskList = MutableLiveData<List<PackageModel>>()

    val taskList: LiveData<List<PackageModel>>
        get() = _taskList

    fun mockTasksData(): List<TaskItemViewHolder> {
        val mockData: MutableList<TaskItemViewHolder> = arrayListOf()
        for (i in 0 until 3) {
            val taskModel = TaskModel(
                taskType = "Prunning",
                staff = Staff(firstName = "Bruno", lastName = "$i"),
                orchardName = "Benji",
                blockName = "UB13",
                rate = 10.0,
                listRows = arrayListOf()
            )
            mockData.add(
                TaskItemViewHolder(
                    if (i == 0) HeaderItem(taskModel.taskType) else null,
                    taskModel
                )
            )
        }
        for (i in 0 until 5) {
            val taskModel = TaskModel(
                taskType = "Thinning",
                staff = Staff(firstName = "Braco", lastName = "$i"),
                orchardName = "Benji",
                blockName = "UB13",
                rate = 10.0,
                listRows = arrayListOf()
            )
            mockData.add(
                TaskItemViewHolder(
                    if (i == 0) HeaderItem(taskModel.taskType) else null,
                    taskModel
                )
            )
        }
        return mockData
    }

}