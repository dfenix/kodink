package middleware

import redux.Action
import redux.Middleware

//fun logger(action: Action): Action {
//    println("Middleware triggered: $action")
//    return action
//}

class Logger : Middleware {
    override fun beforeDispatch(action: Action): Action {
        println("Middleware triggered: $action")
        return action
    }
}