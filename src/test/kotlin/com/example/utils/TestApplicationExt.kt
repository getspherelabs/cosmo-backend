package com.example.utils

import io.ktor.http.*
import io.ktor.server.testing.*


fun TestApplicationEngine.get(url: String) = response(HttpMethod.Get, url, null)
fun TestApplicationEngine.put(url: String, body: String?) = response(HttpMethod.Put, url = url, body)
fun TestApplicationEngine.patch(url: String, body: String?) = response(HttpMethod.Patch, url, body)
fun TestApplicationEngine.post(url: String, body: String?) = response(HttpMethod.Post, url, body)
fun TestApplicationEngine.delete(url: String) = response(HttpMethod.Delete, url)
fun TestApplicationEngine.response(
    method: HttpMethod,
    url: String,
    body: String? = null
): TestApplicationResponse {
    return handleRequest(method, url) {
        if (method != HttpMethod.Get) {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            body?.let { setBody(it) }
        }
    }.response
}