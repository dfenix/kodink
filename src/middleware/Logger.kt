import redux.Action
import redux.State

fun logger(action: Action<State>): Action<State>{
    println("Middleware triggered: $action")
    return action
}