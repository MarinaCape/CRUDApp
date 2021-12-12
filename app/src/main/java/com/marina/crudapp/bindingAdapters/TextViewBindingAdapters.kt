package com.marina.crudapp.bindingAdapters

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.marina.crudapp.extensions.parseDate

@BindingAdapter("setText")
fun TextView.setText(text: Any?) {
    this.text = text?.toString()?:""
}

@BindingAdapter("formatDate")
fun TextView.formatDate(text: String?) {
    text?.let{
        this.text = text.parseDate()
    }
}