package com.networking

import com.networking.base.BaseResponse
import retrofit2.Response

suspend fun <Dto, DomainModel> safeApiCall(
    apiCall: suspend () -> Response<BaseResponse<Dto>>,
    resultExtractor: Response<BaseResponse<Dto>>.() -> Result<DomainModel>,
): Result<DomainModel> {
    return try {
        apiCall().resultExtractor()
    } catch (e: Exception) {
        Result.failure(e)
    }
}

fun Response<*>.getException(): Exception {
    return Exception(
        message(),
    )
}

fun <DomainModel> processResponse(
    isSuccessful: Boolean,
    successfulResponse: DomainModel?,
    exception: Exception,
): Result<DomainModel> {
    return if (isSuccessful && successfulResponse != null) {
        Result.success(successfulResponse)
    } else {
        Result.failure(exception)
    }
}

fun <DomainModel, Dto : BaseDto<DomainModel>> Response<BaseResponse<Dto>>.extractResult(): Result<DomainModel> {
    val successfulResponse = body()?.data?.toDomainModel()
    return processResponse(isSuccessful, successfulResponse, getException())
}

fun <DomainModel, Dto : BaseDto<DomainModel>> Response<Dto>.extractResultStraight(): Result<DomainModel> {
    val successfulResponse = body()?.toDomainModel()
    return processResponse(isSuccessful, successfulResponse, getException())
}

fun <DomainModel, Dto : BaseDto<DomainModel>> Response<BaseResponse<List<Dto>>>.extractListResult(): Result<List<DomainModel>> {
    val successfulResponse = body()?.data?.map { it.toDomainModel() }
    return processResponse(isSuccessful, successfulResponse, getException())
}
