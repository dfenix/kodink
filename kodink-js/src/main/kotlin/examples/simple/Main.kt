package examples.simple

import redux.Action
import redux.Provider.store

fun main(args: Array<String>) {
    /* TODO fun reducer(state: IntState, action: Action): IntState {
        return when (action) {
            is Inc -> state.copy(num = state.num + action.payload)
            is Dec -> state.copy(num = state.num - action.payload)
            else -> state
        }
    }*/

//    TODO store.addReducer(::reducer, "reducer", IntState())
//    TODO store.applyMiddleware(::logger)

    store.dispatch(Inc(1))
    store.dispatch(Inc(2))
    store.dispatch(Inc(22))
    store.dispatch(Dec(1000))
}

// TODO data class IntState(var num: Int = 0) : State
data class Inc(val payload: Int) : Action

data class Dec(val payload: Int) : Action


