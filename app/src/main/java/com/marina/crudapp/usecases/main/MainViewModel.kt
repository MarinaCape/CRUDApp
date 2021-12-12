package com.marina.crudapp.usecases.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.marina.crudapp.App
import com.marina.crudapp.usecases.globals.GlobalAndroidViewModel
import com.marina.data.Repository
import com.marina.domain.dto.user.User
import com.marina.domain.enums.Status
import kotlinx.coroutines.launch

class MainViewModel(app: App, private val repository: Repository) : GlobalAndroidViewModel(app) {

    private var _users = MutableLiveData<ArrayList<User>>()
    val users: LiveData<ArrayList<User>>
        get() = _users

    var searchText: MutableLiveData<String> = MutableLiveData()

    lateinit var clickItem: (User, View) -> Unit

    fun getUsers() {
        status.value = Status.LOADING
        viewModelScope.launch {
            repository.getUsers().also {
                status.value = it.status
                if(it.status != Status.ERROR){
                    _users.value = it.data?: arrayListOf()
                }
            }
        }
    }

    fun deleteUser(id: String){
        status.value = Status.LOADING
        viewModelScope.launch {
            repository.deleteUser(id).also {
                status.value = it.status
                if(it.status == Status.SUCCESS){
                    getUsers()
                }
            }
        }
    }
}