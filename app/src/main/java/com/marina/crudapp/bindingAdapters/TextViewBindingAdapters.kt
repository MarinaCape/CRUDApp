package com.marina.crudapp.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setText")
fun TextView.setText(text: Any?) {
    this.text = text?.toString()?:""
}