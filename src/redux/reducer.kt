package redux

typealias ReducerFunction<S> = (S, Action<S>) -> S

open class Reducer<out S>(val initialState: S) {
    private var function: ReducerFunction<S> = { _,_ -> throw Error("This reducer doesn't have a function attach") }

    constructor(initialState: S, function: ReducerFunction<S>) : this(initialState) {
        this.function = function
    }

    open fun <State> execute(state: State, action: Action<State>): S {
        return function(state as S, action as Action<S>)
    }
}

class MapOfStates<out S>(val stateByKey: Map<String, S>) : State
class SuperReducer<out S>(private val listReducers: Map<String, Reducer<S>>, initialState: MapOfStates<S> = MapOfStates(listReducers.mapValues { it.value.initialState })) : Reducer<S>(initialState as S) {

    override fun <St> execute(states: St, action: Action<St>): S {
        if(states !is MapOfStates<*>) return states as S
        var hasChanged = false
        val nextState: MutableMap<String, S> = mutableMapOf()
        for (reducer in listReducers) {
            val key = reducer.key
            val previousStateForKey = states.stateByKey[key]
            val nextStateForKey = reducer.value.execute(previousStateForKey as S, action as Action<S>)
            nextState[key] = nextStateForKey
            hasChanged = hasChanged || nextStateForKey !== previousStateForKey
        }
        return if (hasChanged) nextState as S else states as S
    }
}