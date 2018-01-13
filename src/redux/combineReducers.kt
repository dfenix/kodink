package redux

//typealias ReducerType = (Any, Action) -> Any

fun combineReducers(vararg reducers: Reducer) {//fun mapByName(params: List<Parameter>): Map<String, Parameter> = params.toMap { it.name }
    for(reducer in reducers){
        println(reducer.javaClass.name)
    }
    val pairs = reducers.map { it to it.toString() }
//    return fun(state: Any, action: Action): Any {
//        var hasChanged = false
//        lateinit var nextState: MutableMap<String, ReducerType>
//        for (key in reducers) {
//            val reducer = reducers.get(key)
//            val previousStateForKey = state.get(key)
//
//            val nextStateForKey = reducer(previousStateForKey, action)!!
//            nextState[key] = nextStateForKey
//            hasChanged = hasChanged || nextStateForKey !== previousStateForKey
//        }
//        return hasChanged ? nextState : stat e
//    }
}