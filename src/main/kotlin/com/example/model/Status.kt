package com.example.model

sealed interface Status {
    object Success : Status
    object Failed : Status
    object NotFound : Status
}