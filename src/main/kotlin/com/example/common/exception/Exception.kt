package com.example.common.exception

data class BadRequestException(override val message: String): Exception(message)

data class NotFoundException(override val message: String): Exception(message)