package redux

open class Reducer<out S>(val initialState: S) {
    private var function: (S, Action<S>) -> S = { _, _ -> throw Error("This reducer doesn't have a function attach") }

    constructor(initialState: S, function: (S, Action<S>) -> S) : this(initialState) {
        this.function = function
    }

    open fun <State> execute(state: State, action: Action<State>): S {
        return function(state as S, action as Action<S>)
    }
}