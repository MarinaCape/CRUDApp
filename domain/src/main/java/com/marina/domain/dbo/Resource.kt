package com.marina.domain.dbo

import com.marina.domain.enums.Status


data class Resource<out T>(val status: Status, val data: T?, val message: String?, val errorCode: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(code: String?, msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg, code)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }
    }
}