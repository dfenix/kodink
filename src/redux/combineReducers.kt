package redux

fun <S>combineReducers(vararg namedReducers: Pair<String,Reducer<S>>): Reducer<S> {
    try {
//        assertReducerShape(namedReducers)
    }catch (e: Exception){
        println(e)
    }
    return SuperReducer(namedReducers.toMap())
}

fun <S> assertReducerShape(reducers: Map<String, Reducer<S>>) {
    for (reducer in reducers){
//        val initialState: EmptyState = reducer.value.execute(action = Action(ActionTypes.INIT.name, EmptyState()))
//        if (initialState.nothing == )
    }
}

//fun combineReducers(vararg reducers: Reducer){
//    val pairs = reducers.map { it to it.toString() }
//}