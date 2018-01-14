package redux

class SuperReducer<out S>(private val listReducers: Map<String, Reducer<S>>, initialState: MapOfStates<S> = MapOfStates(listReducers.mapValues { it.value.initialState })) : Reducer<S>(initialState as S) {

    constructor(vararg reducers: Pair<String, Reducer<S>>) : this(reducers.toMap())

    override fun <St> execute(states: St, action: Action<St>): S {
        if (states !is MapOfStates<*>) return states as S
        var hasChanged = false
        val nextState: MutableMap<String, S> = mutableMapOf()
        for (reducer in listReducers) {
            val key = reducer.key
            val previousStateForKey = states.stateByKey[key]
            val nextStateForKey = reducer.value.execute(previousStateForKey as S, action as Action<S>)
            nextState[key] = nextStateForKey
            hasChanged = hasChanged || nextStateForKey !== previousStateForKey
        }
        return if (hasChanged) MapOfStates(nextState) as S else states as S
    }
}