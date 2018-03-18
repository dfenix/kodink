package examples.counter.reducers

import redux.Action
import redux.IntState
import redux.Reducer

class Increment : Action
class Decrement : Action

class CounterReducer : Reducer({ state, action ->
    if (state is IntState) {
        when (action) {
            is Increment -> IntState(state.value + 1)
            is Decrement -> IntState(state.value - 1)
            else -> state
        }
    } else {
        state
    }
})



