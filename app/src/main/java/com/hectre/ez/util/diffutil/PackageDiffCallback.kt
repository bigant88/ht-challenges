package com.hectre.ez.util.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.hectre.ez.data.domainmodels.PackageModel

class PackageDiffCallback : DiffUtil.ItemCallback<PackageModel>() {
    override fun areItemsTheSame(oldItem: PackageModel, newItem: PackageModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PackageModel, newItem: PackageModel): Boolean {
        return oldItem == newItem //This equality check will check all the fields, because PackageReceive is a data class
    }

}