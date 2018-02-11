import redux.Action

fun logger(action: Action): Action {
    println("Middleware triggered: $action")
    return action
}