package redux

typealias ReducerType = (Any, Action) -> Any

fun combineReducers(reducers: Map<String, ReducerType>) {
    return fun(state: Any, action: Action): Any {
        var hasChanged = false
        lateinit var nextState: MutableMap<String, ReducerType>
        for (key in reducers) {
            val reducer = reducers.get(key)
            val previousStateForKey = state.get(key)

            val nextStateForKey = reducer(previousStateForKey, action)!!
            nextState[key] = nextStateForKey
            hasChanged = hasChanged || nextStateForKey !== previousStateForKey
        }
        return hasChanged ? nextState : stat e
    }
}