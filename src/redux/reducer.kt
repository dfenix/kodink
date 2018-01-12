package redux

class Reducer{
    private lateinit var subscription: (State, Action<State>) -> State

    fun <S>subscribe(reducer: (S, Action<S>) -> S){
        subscription = reducer as (State, Action<State>) -> State
    }
}