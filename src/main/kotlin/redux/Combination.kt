package redux

class Combination(val reducers: Map<String, Reducer>) : IReducer{
    override val reduce = { state: Any, action: Action ->
        if (state is Object) {
            var hasChanged = false
            val nextState = mutableMapOf<String, Any>()
            for (reducer in reducers) {
                val key = reducer.key
                val previousStateForKey = state.props[key]!!
                try {
                    var nextStateForKey = reducer.value.reduce(previousStateForKey, action)
                    nextState[key] = nextStateForKey
                    hasChanged = hasChanged || nextStateForKey !== previousStateForKey
                } catch (e: Exception) {
                    nextState[key] = previousStateForKey
                    println(e)
                }
            }
            if (hasChanged) nextState
        }
        state
    }
}

open class Object(val props: MutableMap<String, State> = mutableMapOf()) : State


