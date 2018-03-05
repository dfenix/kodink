package examples.counter.reducers

import redux.Action
import redux.State

data class CounterState(val counter: Int = 0)//TODO : State
class Increment : Action
class Decrement : Action

fun counterReducer(state: CounterState, action: Action): CounterState {
    return when (action) {
        is Increment -> CounterState(state.counter + 1)
        is Decrement -> CounterState(state.counter - 1)
        else -> state
    }
}



