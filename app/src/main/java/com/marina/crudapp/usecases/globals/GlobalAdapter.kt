package com.marina.crudapp.usecases.globals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class GlobalAdapter(
    private var layout: Int,
    private var listItems: MutableList<out Any>? = null,
    private var viewModel: GlobalAndroidViewModel? = null
) :
    RecyclerView.Adapter<ViewHolderGlobal>() {

    override fun getItemCount(): Int {
        return listItems?.size ?: 0
    }

    open fun getItem(index: Int): Any? = listItems?.get(index)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGlobal {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, layout, parent, false)
        return ViewHolderGlobal(binding, viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolderGlobal, position: Int) {
        holder.bind(getItem(position))
    }

    fun setItemLayout(_layout: Int) {
        this.layout = _layout
    }


    fun setItems(items: List<Any>?) {
        listItems = items?.toMutableList()
        notifyDataSetChanged()
    }

    fun getAllItems(): List<Any> = listItems ?: mutableListOf()

    fun addItems(items: List<Any>, index: Int = 0) {
        listItems?.addAll(index, items as Collection<Nothing>)
    }

    fun removeItem(index: Int) {
        listItems?.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addItem(item: Any, index: Int = 0) {
        if (listItems == null)
            listItems = mutableListOf()
        (listItems as MutableList<Any>?)?.add(index, item)
        notifyItemInserted(index)
    }

    fun addItemToLast(item: Any) {
        if (listItems == null)
            listItems = mutableListOf()
        (listItems as MutableList<Any>?)?.add(item)
        notifyDataSetChanged()
    }

}