package com.project.khabri.domain


typealias RootError = APIError

sealed interface Resource<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : Resource<D, E>
    data class Error<out D, out E : RootError>(val error: E, val data: D? = null) : Resource<D, E>
}