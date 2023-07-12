package com.example.http


interface Response {
    val status: Status
    val message: String
}