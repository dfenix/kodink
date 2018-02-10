package middleware

import redux.Action
import redux.State
import java.time.LocalDateTime

fun timer(next: (Action<State>) -> Action<State>, action: Action<State>){
    var current = LocalDateTime.now()
    println("Current time: $current")
    next(action)
}