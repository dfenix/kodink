package redux

interface Reducer {
    fun execute(state: State, action: Action): State
}