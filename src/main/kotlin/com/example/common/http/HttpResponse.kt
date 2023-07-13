package com.example.common.http

import io.ktor.http.HttpStatusCode

sealed class HttpResponse<TYPE : Response> {
    abstract val body: TYPE
    abstract val code: HttpStatusCode

    data class Success<TYPE : Response>(override val body: TYPE) : HttpResponse<TYPE>() {
        override val code: HttpStatusCode = HttpStatusCode.OK
    }

    data class NotFound<TYPE : Response>(override val body: TYPE) : HttpResponse<TYPE>() {
        override val code: HttpStatusCode = HttpStatusCode.NotFound
    }

    data class BadRequest<TYPE : Response>(override val body: TYPE) : HttpResponse<TYPE>() {
        override val code: HttpStatusCode = HttpStatusCode.BadRequest
    }

    companion object {
        fun <TYPE : Response> success(response: TYPE) = Success(body = response)
        fun <TYPE : Response> notFound(response: TYPE) = NotFound(body = response)
        fun <TYPE : Response> badRequest(response: TYPE) = BadRequest(body = response)
    }
}

fun httpResponse(response: Response): HttpResponse<Response> {
    return when(response.status) {
        Status.Failed -> HttpResponse.badRequest(response)
        Status.NotFound -> HttpResponse.notFound(response)
        Status.Success -> HttpResponse.success(response)
    }
}