package com.marina.crudapp.bindingAdapters

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.marina.crudapp.extensions.parseDate

@BindingAdapter("formatDate")
fun EditText.formatDate(text: String?) {
    text?.let{
        this.setText(text.parseDate())
    }
}