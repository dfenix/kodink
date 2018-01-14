package redux

fun <S> combineReducers(vararg namedReducers: Pair<String, Reducer<S>>): Reducer<S> {
    return SuperReducer(namedReducers.toMap())
}

//fun combineReducers(vararg reducers: Reducer){
//    val pairs = reducers.map { it to it.toString() }
//}