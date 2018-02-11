package middleware

import redux.Action
import java.time.LocalDateTime

fun timer(action: Action): Action {
    var current = LocalDateTime.now()
    println("Current time: $current")
    return action
}