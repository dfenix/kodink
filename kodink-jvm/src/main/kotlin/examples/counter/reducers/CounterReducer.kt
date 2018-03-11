package examples.counter.reducers

import redux.Action
import redux.Reducer

class Increment : Action
class Decrement : Action

class CounterReducer : Reducer({ state, action ->
    if (state is Int) {
        when (action) {
            is Increment -> state + 1
            is Decrement -> state - 1
            else -> state
        }
    } else {
        state
    }
})



