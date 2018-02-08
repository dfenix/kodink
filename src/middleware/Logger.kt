import redux.Action
import redux.State

//fun logger(getState: Any, dispatch: Any): Any{
//    return fun(next: () -> Any): Function<State> {
//        return fun(action: Action<State>): Any {
//            println("ACTION: ${action.type}", action)
//            next(action)
//        }
//    }
//}

fun logger(next: (Action<State>) -> Action<State>, action: Action<State>){
    println("Middleware triggered: $action")
    next(action)
}