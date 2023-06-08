package com.example.model

sealed interface State {
    object Success : State
    object Failed : State
    object NotFound : State
}