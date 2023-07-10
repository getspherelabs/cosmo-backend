package com.example.model.response

import com.example.model.Response
import com.example.model.Status
import kotlinx.serialization.Serializable

@Serializable
data class FailureResponse(override val status: Status, override val message: String): Response
