package redux

interface Middleware {
    fun beforeDispatch(action: Action): Action
}