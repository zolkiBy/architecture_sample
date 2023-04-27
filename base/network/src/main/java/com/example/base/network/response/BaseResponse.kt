package com.example.base.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {

    @SerialName("error")
    var error: Boolean? = null

    @SerialName("status")
    var responseStatus: Int? = null

    @SerialName("message")
    var message: String? = null

    @SerialName("description")
    var description: String? = null

    fun isSuccess(): Boolean {
        return error == null || error == false
    }

    fun isError(): Boolean {
        return error != null && error == true
    }
}