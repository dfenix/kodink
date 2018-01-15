package redux

class SuperReducer<out S>(private val listReducers: Map<String, Reducer<S>>, initialState: S = MapOfStates(listReducers.mapValues { it.value.initialState }) as S) : Reducer<S>(initialState) {

    constructor(vararg reducers: Pair<String, Reducer<S>>) : this(reducers.toMap())

    override fun <St> execute(state: St, action: Action<St>): S {
        if (state !is MapOfStates<*>) return state as S
        var hasChanged = false
        val nextState: MutableMap<String, S> = mutableMapOf()
        for (reducer in listReducers) {
            val key = reducer.key
            val previousStateForKey = state.stateByKey[key]
            val nextStateForKey = reducer.value.execute(previousStateForKey as S, action as Action<S>)
            nextState[key] = nextStateForKey
            hasChanged = hasChanged || nextStateForKey !== previousStateForKey
        }
        return if (hasChanged) MapOfStates(nextState) as S else state as S
    }
}