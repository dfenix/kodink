package examples.simple

import logger
import redux.Action
import redux.Provider.store
import redux.State

fun main(args: Array<String>) {
    fun reducer(state: IntState, action: Action): IntState {
        return when (action) {
            is Inc -> state.copy(num = state.num + action.payload)
            is Dec -> state.copy(num = state.num - action.payload)
            else -> state
        }
    }

    store.addReducer(::reducer, "reducer", IntState())
    store.applyMiddleware(::logger)

    store.dispatch(Inc(1))
    store.dispatch(Inc(2))
    store.dispatch(Inc(22))
    store.dispatch(Dec(1000))
}

data class IntState(var num: Int = 0) : State
data class Inc(val payload: Int) : Action
data class Dec(val payload: Int) : Action


