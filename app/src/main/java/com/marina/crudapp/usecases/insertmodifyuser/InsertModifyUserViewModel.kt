package com.marina.crudapp.usecases.insertmodifyuser

import androidx.lifecycle.viewModelScope
import com.marina.crudapp.App
import com.marina.crudapp.usecases.globals.GlobalAndroidViewModel
import com.marina.data.Repository
import com.marina.domain.enums.Status
import com.marina.domain.request.UserRequest
import kotlinx.coroutines.launch

class InsertModifyUserViewModel(app: App, private val repository: Repository) : GlobalAndroidViewModel(app) {

    lateinit var finishAction: () -> Unit

    fun modifyUser(user: UserRequest){
        status.value = Status.LOADING
        viewModelScope.launch {
            repository.modifyUser(user).also {
                status.value = it.status
                if(it.status == Status.SUCCESS){
                    finishAction.invoke()
                }
            }
        }
    }

    fun insertUser(user: UserRequest){
        status.value = Status.LOADING
        viewModelScope.launch {
            repository.insertUser(user).also {
                status.value = it.status
                if(it.status == Status.SUCCESS){
                    finishAction.invoke()
                }
            }
        }
    }
}