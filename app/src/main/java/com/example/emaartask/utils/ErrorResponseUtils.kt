package com.example.emaartask.utils

import com.example.emaartask.data.model.response.FailureResponse
import retrofit2.HttpException
import java.io.IOException

typealias OnFailureResponse = (FailureResponse) -> Unit
class ErrorResponseUtils {

    companion object {
        private const val BAD_REQUEST_ERROR_MESSAGE = "Bad Request!"
        private const val FORBIDDEN_ERROR_MESSAGE = "Forbidden!"
        private const val NOT_FOUND_ERROR_MESSAGE = "Not Found!"
        private const val METHOD_NOT_ALLOWED_ERROR_MESSAGE = "Method Not Allowed!"
        private const val CONFLICT_ERROR_MESSAGE = "Conflict!"
        private const val UNAUTHORIZED_ERROR_MESSAGE = "Unauthorized!"
        private const val INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server error!"
        private const val IO_EXCEPTIONS = "IO Exceptions!"

        fun error(throwable: Throwable, onFailureResponse: OnFailureResponse) {
            when (throwable) {
                is IOException -> {
                    FailureResponse(
                        0,
                        IO_EXCEPTIONS,
                        ""
                    )
                }
                is HttpException -> {
                    when (throwable.code()) {
                        400 -> onFailureResponse(
                            FailureResponse(
                                throwable.code(),
                                throwable.message(),
                                BAD_REQUEST_ERROR_MESSAGE
                            )
                        )
                        401 -> {
                            FailureResponse(
                                throwable.code(),
                                throwable.message(),
                                UNAUTHORIZED_ERROR_MESSAGE
                            )
                        }
                        403 -> onFailureResponse(
                            FailureResponse(
                                throwable.code(),
                                throwable.message(),
                                FORBIDDEN_ERROR_MESSAGE
                            )
                        )
                        404 ->
                            onFailureResponse(
                                FailureResponse(
                                    throwable.code(),
                                    throwable.message(),
                                    NOT_FOUND_ERROR_MESSAGE
                                )
                            )
                        405 ->
                            onFailureResponse(
                                FailureResponse(
                                    throwable.code(),
                                    throwable.message(),
                                    METHOD_NOT_ALLOWED_ERROR_MESSAGE
                                )
                            )
                        409 ->
                            onFailureResponse(
                                FailureResponse(
                                    throwable.code(),
                                    throwable.message(),
                                    CONFLICT_ERROR_MESSAGE
                                )
                            )
                        500 ->
                            onFailureResponse(
                                FailureResponse(
                                    throwable.code(),
                                    throwable.message(),
                                    INTERNAL_SERVER_ERROR_MESSAGE
                                )
                            )
                        else ->
                            onFailureResponse(
                                FailureResponse(
                                    0,
                                    throwable.message(),
                                    throwable.message()
                                )
                            )
                    }
                }
                else -> {
                    FailureResponse(
                        0,
                        throwable.message!!,
                        throwable.message!!
                    )
                }
            }
        }
    }
}