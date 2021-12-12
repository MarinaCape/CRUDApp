package com.marina.crudapp.usecases.insertmodifyuser

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.marina.crudapp.R
import com.marina.crudapp.databinding.ActivityInsertModifyUserBinding
import com.marina.crudapp.extensions.parseDate
import com.marina.crudapp.extensions.parseDateComplete
import com.marina.crudapp.extensions.parseStringToDate
import com.marina.crudapp.parcelables.ParcelableHelperMethods.toUndoParcelable
import com.marina.crudapp.parcelables.UserParcelable
import com.marina.crudapp.utils.PARAM_IS_MODIFY
import com.marina.crudapp.utils.PARAM_USER
import com.marina.domain.request.UserRequest
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class InsertModifyUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertModifyUserBinding
    private val viewModel by viewModel<InsertModifyUserViewModel>()
    private var isModify: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_insert_modify_user)

        binding.viewModel = viewModel
        binding.user = intent.getParcelableExtra<UserParcelable>(PARAM_USER)?.toUndoParcelable()
        isModify = intent.getBooleanExtra(PARAM_IS_MODIFY, false)

        with(viewModel){
            finishAction = ::finish
            dismissAction = ::finish
            clickView = ::handleClicks
        }
        configFields()

        binding.lifecycleOwner = this
    }

    //------------ SETUP --------------
    //region SETUP
    private fun configFields(){
        binding.editBirthdate.setOnClickListener {
            val calendar = Calendar.getInstance()
            if(binding.editBirthdate.text.toString().isNotEmpty()) {
                calendar.time = binding.editBirthdate.text.toString().parseStringToDate() ?: Date()
            }
            val datePickerDialog = DatePickerDialog(
                binding.root.context,
                { _, year, month, dayOfMonth ->
                    val chosenDate = Calendar.getInstance()
                    chosenDate.set(year, month, dayOfMonth)
                    binding.editBirthdate.setText(
                        SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                            .format(chosenDate.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }

    private fun handleClicks(v: View){
        when(v.id){
            R.id.btnSave -> {
                when{
                    isModify -> {
                        //Both fields could be nullables, so it is not necessary to be fill in
                        viewModel.modifyUser(
                            UserRequest(
                                binding.user?.id,
                                binding.editName.text.toString(),
                                binding.editBirthdate.text.toString().parseDateComplete()
                            )
                        )
                    }
                    else ->{
                        viewModel.insertUser(
                            UserRequest(null,
                                name = binding.editName.text.toString(),
                                birthdate = binding.editBirthdate.text.toString()
                            )
                        )
                    }
                }
            }
        }
    }
    //endregion
}