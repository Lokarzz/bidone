package com.bidone.domain.util.flow

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.util.throwable.handleError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart


internal fun <T, K> Flow<T>.handleAPIState(
    flowCollector: FlowCollector<APIState<K>>,
): Flow<T> {
    return onStart { flowCollector.emit(APIState.Loading) }
        .catch {
            val apiError = it.handleError()
            flowCollector.emit(APIState.Error(apiError))
        }
}

internal fun <T> Flow<APIState<T>>.applyDefaultState(): Flow<APIState<T>> {
    return onStart { emit(APIState.Loading) }.catch { emit(APIState.Error(it.handleError())) }
}