package redux

//class Reducer{
//    private lateinit var subscription: (State, Action<State>) -> State
//
//    fun <S>subscribe(reducer: (S, Action<S>) -> S){
//        subscription = reducer as (State, Action<State>) -> State
//    }
//}

open class Reducer<out S>(private val function: (S, Action<S>) -> S) {
    open fun <State>execute(state: State = EmptyState() as State, action: Action<State>): S {
        return function(state as S , action as Action<S>)
    }
}

class SuperReducer<out S>(private val listReducers: Map<String, Reducer<S>>): Reducer<S>({state: S, action: Action<S> -> state}){
    private val states: Map<String, S> = mutableMapOf()
    override fun <St>execute(state: St, action: Action<St>): S {
        var hasChanged = false
        val nextState: MutableMap<String, S> = mutableMapOf()
        for (reducer in listReducers){
            val key = reducer.key
            val previousStateForKey = states[key]
            val nextStateForKey = reducer.value.execute(previousStateForKey as S, action as Action<S>)
            nextState[key] = nextStateForKey
            hasChanged = hasChanged || nextStateForKey !== previousStateForKey
        }
        return if(hasChanged) nextState as S else state as S
    }
}