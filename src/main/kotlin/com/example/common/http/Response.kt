package com.example.common.http


interface Response {
    val status: Status
    val message: String
}