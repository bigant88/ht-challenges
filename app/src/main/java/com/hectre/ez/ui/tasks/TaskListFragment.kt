package com.hectre.ez.ui.tasks

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.hectre.ez.EzApplication
import com.hectre.ez.R
import com.hectre.ez.databinding.FragmentTaskListBinding
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IHeader

class TaskListFragment : Fragment(), OnParameterSelectedListener {

    private val taskViewModel by viewModels<TaskViewModel> {
        TaskViewModelFactory((requireActivity().application as EzApplication).packagesRepository)
    }
    private var mAdapter: TasksAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTaskListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false)
        val application = requireNotNull(this.activity).application
        binding.viewModel = taskViewModel
        binding.lifecycleOwner = this
        setupRecyclerView(binding, application)
        return binding.root
    }

    private fun setupRecyclerView(
        binding: FragmentTaskListBinding,
        application: Application?
    ) {
        // Initialize Adapter and RecyclerView
        // ExampleAdapter makes use of stableIds, I strongly suggest to implement 'item.hashCode()'
        FlexibleAdapter.useTag("HeadersSectionsAdapter")
        mAdapter = TasksAdapter(taskViewModel.mockTasksData(), activity)
        mAdapter!!.setDisplayHeadersAtStartUp(true)
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.setHasFixedSize(true) //Size of RV will not change
        // NOTE: Use default item animator 'canReuseUpdatedViewHolder()' will return true if
        // a Payload is provided. FlexibleAdapter is actually sending Payloads onItemChange.
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        val decoration = DividerItemDecoration(application, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)

    }

    override fun onParameterSelected(itemType: Int, referencePosition: Int, childPosition: Int) {

    }

    override fun getReferenceList(): List<IHeader<*>?> {
        return mAdapter!!.headerItems
    }

}
