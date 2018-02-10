package middleware

import redux.Action
import redux.State
import java.time.LocalDateTime

fun timer(action: Action<State>): Action<State>{
    var current = LocalDateTime.now()
    println("Current time: $current")
    return action
}