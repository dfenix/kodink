package redux

inline fun <reified State>superReducer2(listReducers: Map<String, ReducerType<State>>) : ReducerType<State>{

    //constructor(vararg reducers: Pair<String, Reducer<S>>) : this(reducers.toMap())

    return fun (state: State, action: Action<State>): State {
        if (state !is MapOfStates<*>) return state
        var hasChanged = false
        val nextState: MutableMap<String, State> = mutableMapOf()
        for (reducer in listReducers) {
            val key = reducer.key
            val previousStateForKey = state.stateByKey[key]as State
            val nextStateForKey = reducer.value(previousStateForKey, action)
            nextState[key] = nextStateForKey
            hasChanged = hasChanged || nextStateForKey !== previousStateForKey
        }
        return if (hasChanged) MapOfStates(nextState) as State else state
    }
}