package com.example.common.http


import kotlinx.serialization.Serializable

@Serializable
data class FailureResponse(override val status: Status, override val message: String): Response
