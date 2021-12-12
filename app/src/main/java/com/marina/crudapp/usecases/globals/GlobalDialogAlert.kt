package com.marina.crudapp.usecases.globals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.marina.crudapp.BR
import com.marina.crudapp.R
import com.marina.data.extensions.log

open class GlobalDialogAlert(
    private var layout: Int? = null,
    private var titleDialog: String? = null,
    private var contentText: String? = null,
    private var positiveText: String? = null,
    private var negativeText: String? = null,
    private var positive: () -> Unit = {},
    private var negative: () -> Unit = {},
    private var canCancel: Boolean = false
) : AppCompatDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogTheme)
        isCancelable = canCancel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, layout ?: R.layout.dialog_alert, container, false)
        binding.apply {
            setVariable(BR.title, titleDialog)
            setVariable(BR.content, contentText)
            setVariable(BR.positiveButton, positiveText)
            setVariable(BR.negativeButton, negativeText)
        }

        val positiveButton: Button? = binding.root.findViewById(R.id.buttonPositive)
        positiveButton?.setOnClickListener {
            dismiss()
            positive.invoke()
        }
        val negativeButton: View? = binding.root.findViewById(R.id.buttonNegative)
        negativeButton?.setOnClickListener {
            negative.invoke()
            dismiss()
        }

        return binding.root
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commit()
        } catch (e: IllegalStateException) {
            e.message.log()
        }
    }

}