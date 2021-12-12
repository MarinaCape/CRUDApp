package com.marina.crudapp.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.marina.crudapp.usecases.globals.GlobalDialogAlert
import java.util.*


fun Context.goToActivity(clazz: Class<*>, extras: Map<String, Any>? = null, vararg flags: Int) {
    val intent = Intent(this, clazz)
    flags.forEach { intent.addFlags(it) }
    intent.addExtras(extras)
    this.startActivity(intent)
}

fun Context.goToActivity(clazz: Class<*>, vararg flags: Int) {
    val intent = Intent(this, clazz)
    flags.forEach { intent.addFlags(it) }
    this.startActivity(intent)
}

inline fun <reified T : Activity> Context.goToActivity(extras: Map<String, Any>? = null) {
    val intent = Intent(this, T::class.java)
    intent.addExtras(extras)
    this.startActivity(intent)
}

fun Intent.addExtras(extras: Map<String, Any>? = null) {
    extras?.forEach {
        when (it.value) {
            is Int -> this.putExtra(it.key, it.value as Int)
            is String -> this.putExtra(it.key, it.value as String)
            is Boolean -> this.putExtra(it.key, it.value as Boolean)
            is Long -> this.putExtra(it.key, it.value as Long)
            is Double -> this.putExtra(it.key, it.value as Double)
            is Parcelable -> this.putExtra(it.key, it.value as Parcelable)
            is List<*> -> this.putParcelableArrayListExtra(
                it.key,
                it.value as ArrayList<out Parcelable>
            )
        }
    }
}

fun AppCompatActivity.showAlertDialog(
    layout: Int? = null,
    titleDialog: String? = null,
    contentText: String? = null,
    positiveText: String? = null,
    negativeText: String? = null,
    positive: () -> Unit = {},
    negative: () -> Unit = {},
    cancelable: Boolean = false
): GlobalDialogAlert {

    val dialog = GlobalDialogAlert(layout, titleDialog, contentText, positiveText, negativeText, positive, negative, cancelable)
    dialog.show(
        this.supportFragmentManager,
        "GLOBAL_DIALOG_TAG"
    )
    return dialog
}



