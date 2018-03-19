package redux

open class Reducer(override val reduce: (State, Action) -> State) : IReducer

interface IReducer {
    val reduce: (State, Action) -> State
}

fun combineReducers(vararg reducers: Pair<String, IReducer>) = Combination(reducers.toMap())
