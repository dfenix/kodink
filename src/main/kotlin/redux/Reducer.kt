package redux

class Reducer(override val reduce: (Any, Action) -> Any) : IReducer

interface IReducer{
    val reduce: (Any, Action) -> Any
}

fun combineReducers(vararg reducers: Pair<String, Reducer>) = Combination(reducers.toMap())
