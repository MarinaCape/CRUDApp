package com.marina.crudapp.usecases.globals

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.marina.crudapp.BR

open class ViewHolderGlobal(
    private val binding: ViewDataBinding,
    private val viewModel: GlobalAndroidViewModel?
) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(bindableObject: Any?) {
        binding.setVariable(BR.itemObject, bindableObject)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }
}