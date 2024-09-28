package com.farmtech.weighingmachine.utils

import com.apollographql.apollo.api.Error as ApiError
import com.apollographql.apollo.api.Mutation
import com.farmtech.weighingmachine.apolloClient
import com.farmtech.weighingmachine.ui.components.utils.isEmpty

sealed class GraphqlError {
    data class Error(val errors: List<ApiError>?) : GraphqlError()
    data class Messages(val message: String?) : GraphqlError()
    data class Exception(val exception: kotlin.Exception) : GraphqlError()
}

suspend fun <D : Mutation.Data> onMutation(
    mutation: Mutation<D>,
    onSuccess: (D?) -> Unit = {},
    onError: (GraphqlError?) -> Unit = {}
) {
    try {
        val response = apolloClient.mutation(mutation).execute()

        when {
            isEmpty(response) -> {
                onError(null)
            }

            response.hasErrors() -> {
                val errorMessages = response.errors?.joinToString { it.message } ?: null
                onError(GraphqlError.Error(response.errors))
                onError(GraphqlError.Messages(errorMessages))
            }

            else -> {
                onSuccess(response.data)
            }
        }
    } catch (e: Exception) {
        onError(GraphqlError.Exception(e))
    }
}