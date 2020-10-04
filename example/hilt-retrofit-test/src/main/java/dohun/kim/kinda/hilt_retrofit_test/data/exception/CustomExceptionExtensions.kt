package dohun.kim.kinda.hilt_retrofit_test.data.exception

import retrofit2.HttpException

fun Exception.getCustomHttpException(): Exception =
    when (this) {
        is HttpException -> {
            when (code()) {
                403 -> ForbiddenException()
                500 -> InternalErrorException()
                else -> InternalErrorException()
            }
        }
        else -> InternalErrorException()
    }