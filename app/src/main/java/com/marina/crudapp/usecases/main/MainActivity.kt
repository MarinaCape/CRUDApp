package com.marina.crudapp.usecases.main

import android.os.Bundle
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import com.marina.crudapp.databinding.ActivityMainBinding
import com.marina.crudapp.extensions.goToActivity
import com.marina.crudapp.extensions.observe
import com.marina.crudapp.extensions.showAlertDialog
import com.marina.crudapp.usecases.globals.GlobalActivity
import com.marina.crudapp.usecases.globals.GlobalAdapter
import com.marina.crudapp.usecases.insertmodifyuser.InsertModifyUserActivity
import com.marina.crudapp.utils.PARAM_IS_MODIFY
import com.marina.crudapp.utils.PARAM_USER
import com.marina.domain.dto.user.User
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.*
import com.marina.crudapp.R
import com.marina.crudapp.parcelables.ParcelableHelperMethods.toParcelable


class MainActivity : GlobalActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        with(viewModel){
            getUsers()
            clickItem = ::handleClickItems
            clickView = ::handleClicks
            observe(users, ::setUpUsers)
            observe(searchText, ::observeSearchText)
        }

        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUsers()
    }

    //------------ SETUP --------------
    //region SETUP
    private fun handleClicks(v: View){
        when(v.id){
            R.id.fabInsert -> goToActivity<InsertModifyUserActivity>()
        }
    }
    //endregion


    //------------ OBSERVERS --------------
    //region OBSERVERS
    private fun setUpUsers(users: ArrayList<User>?){
        users?.let{
            binding.recycler.adapter = GlobalAdapter(R.layout.item_user, it, viewModel)
        }
    }

    private fun observeSearchText(searchText: String?){
        searchText?.let{ text ->
            (binding.recycler.adapter as GlobalAdapter).setItems(viewModel.users.value?.filter { it.name?.contains(text, ignoreCase = true)?: false })
        }
    }
    //endregion

    //------------ ACTIONS --------------
    //region ACTIONS
    private fun handleClickItems(user: User, view: View){
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.menu_options)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_modify -> {
                    goToActivity<InsertModifyUserActivity>(hashMapOf(PARAM_IS_MODIFY to true, PARAM_USER to user.toParcelable()))
                    true
                }
                R.id.menu_remove -> {
                    showAlertDialog(R.layout.dialog_alert,
                        titleDialog = getString(R.string.title_remove),
                        contentText = getString(R.string.content_remove),
                        positiveText = getString(R.string.menu_remove),
                        negativeText = getString(R.string.cancel),
                        positive = {
                            viewModel.deleteUser(user.id)
                        }
                    )
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
    //endregion
}