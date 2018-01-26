package demo.reducers

import redux.Action
import redux.Reducer
import redux.State

data class CounterState(val counter: Int = 0) : State
class CounterReducer : Reducer<CounterState>(CounterState(),
        { state: CounterState, action: Action<CounterState> ->
            when (action.type) {
                "INCREMENT" -> CounterState(state.counter + 1)
                "DECREMENT" -> CounterState(state.counter - 1)
                else -> state
            }
        }
)


