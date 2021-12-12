package com.marina.data.cloud

import com.google.gson.Gson
import com.marina.data.extensions.log
import com.marina.domain.dbo.Resource
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


open class ResponseHandler {
    companion object {
        const val TIME_OUT = 3
        const val AUTHENTICATION_FAILS = 4
        const val AUTHENTICATION_FAILS_MESSAGE = "Authentication fails"
    }

    fun <T> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        e.message?.log()
        return when (e) {
            is HttpException -> {
                Resource.error(e.code().toString() ?: "", e.response()?.errorBody()?.string() ?: getErrorMessage(e.code()), null)
            }
            is SocketTimeoutException -> Resource.error(
                null,
                e.message ?: getErrorMessage(TIME_OUT),
                null
            )
            is IOException -> Resource.error(null, e.message ?: getErrorMessage(code = AUTHENTICATION_FAILS), null)
            else -> Resource.error(null, e.message ?: getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            TIME_OUT -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            AUTHENTICATION_FAILS -> AUTHENTICATION_FAILS_MESSAGE
            else -> "Something went wrong"
        }
    }
}