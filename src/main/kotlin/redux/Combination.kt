package redux

class Combination(val reducers: Map<String, Reducer>) : IReducer {
    override val reduce = { state: Any, action: Action ->
        if (state is States) {
            var hasChanged = false
            val nextState = States()
            for (reducer in reducers) {
                val key = reducer.key
                val previousStateForKey = state[key]!!
                try {
                    var nextStateForKey = reducer.value.reduce(previousStateForKey, action)
                    nextState[key] = nextStateForKey
                    hasChanged = hasChanged || nextStateForKey !== previousStateForKey
                } catch (e: Exception) {
                    nextState[key] = previousStateForKey
                    println(e)
                }
            }
            if (hasChanged) {
                nextState
            } else {
                state
            }
        } else {
            state
        }
    }
}


