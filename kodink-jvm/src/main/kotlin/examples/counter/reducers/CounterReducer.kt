package examples.counter.reducers

import redux.Action
import redux.State

data class CounterState(val counter: Int = 0) : State
data class CounterAction(override val type: String) : Action

fun counterReducer(state: CounterState, action: Action): CounterState {
    return when (action.type) {
        "INCREMENT" -> CounterState(state.counter + 1)
        "DECREMENT" -> CounterState(state.counter - 1)
        else -> state
    }
}



