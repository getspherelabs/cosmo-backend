package com.example.common.http

import com.example.common.http.Response
import com.example.common.http.Status
import kotlinx.serialization.Serializable

@Serializable
data class FailureResponse(override val status: Status, override val message: String): Response
