package com.example.http.response

import com.example.http.Response
import com.example.http.Status
import kotlinx.serialization.Serializable

@Serializable
data class FailureResponse(override val status: Status, override val message: String): Response
