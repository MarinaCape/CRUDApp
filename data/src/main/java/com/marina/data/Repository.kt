package com.marina.data


import android.content.SharedPreferences
import android.provider.Settings.Global.putString
import com.marina.data.cloud.ResponseHandler
import com.marina.data.cloud.RestApi
import com.marina.domain.dbo.Resource
import com.marina.domain.dto.user.User
import com.marina.domain.request.UserRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.Response
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


class Repository(
    private val restApi: RestApi,
    private val responseHandler: ResponseHandler,
    private val ioDispatcher: CoroutineDispatcher,
    private val prefs: SharedPreferences
) {

    //------------ SERVICES --------------
    //region SERVICES
    suspend fun getUsers(): Resource<ArrayList<User>> {
        return withContext(ioDispatcher) {
            try {
                responseHandler.handleSuccess(restApi.getUsers())
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }

    suspend fun deleteUser(idUser: String): Resource<Any> {
        return withContext(ioDispatcher) {
            try {
                responseHandler.handleSuccess(restApi.deleteUser(idUser))
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }

    suspend fun insertUser(user: UserRequest): Resource<Any> {
        return withContext(ioDispatcher) {
            try {
                responseHandler.handleSuccess(restApi.insertUser(user))
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }

    suspend fun modifyUser(user: UserRequest): Resource<Any> {
        return withContext(ioDispatcher) {
            try {
                responseHandler.handleSuccess(restApi.modifyUser(user))
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }

    //endregion

    //------------ PREFERENCES --------------
    //region PREFERENCES

   /* fun getRoadAssistancePhone() = prefs.getString(PREF_ROAD_ASSISTANCE_PHONE, null)

    private fun saveCustomerAssistancePhone(phone: String) {
        prefs.edit {
            putString(PREF_CUSTOMER_ASSISTANCE_PHONE, phone)
        }
    }*/

}