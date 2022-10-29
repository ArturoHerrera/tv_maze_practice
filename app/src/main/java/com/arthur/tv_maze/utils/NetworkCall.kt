package com.arthur.tv_maze.utils

import android.os.Build
import retrofit2.HttpException
import java.net.UnknownHostException
import java.time.temporal.UnsupportedTemporalTypeException

suspend fun <T> networkCall(
    call: suspend () -> T
): ServiceResult<T> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    try {
        ServiceResult.Success(dto = call())
    } catch (e: UnknownHostException) {
        ServiceResult.Error(message = "Revisa tu conexión a internet.")
    } catch (e: HttpException) {
        ServiceResult.Error(message = HttpError.fromCode(e.code()).errorMsg)
    } catch (e: UnsupportedTemporalTypeException){
        ServiceResult.Error(message = "Campo de fecha inválido: " + e.message!!)
    } catch (e: Exception) {
        ServiceResult.Error(message = "Ocurrio un problema inesperado")
    }
} else {
    try {
        ServiceResult.Success(dto = call())
    } catch (e: UnknownHostException) {
        ServiceResult.Error(message = "Revisa tu conexión a internet.")
    } catch (e: HttpException) {
        ServiceResult.Error(message = HttpError.fromCode(e.code()).errorMsg)
    } catch (e: Exception) {
        ServiceResult.Error(message = "Ocurrio un problema inesperado")
    }
}