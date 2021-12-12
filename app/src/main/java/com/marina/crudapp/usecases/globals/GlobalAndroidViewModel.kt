package com.marina.crudapp.usecases.globals

import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.marina.crudapp.App
import com.marina.domain.enums.Status

open class GlobalAndroidViewModel(val app: App) : AndroidViewModel(app) {

    lateinit var dismissAction: () -> Unit
    open lateinit var clickView: (view: View) -> Unit
    open lateinit var showErrorAlert: (message: String) -> Unit
    var status: MutableLiveData<Status> = MutableLiveData()

}