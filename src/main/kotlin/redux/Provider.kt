package redux

object Provider {
    lateinit var store: Store
    fun createStore(reducer: IReducer, initialState: State) {
        this.store = Store(reducer, initialState)
    }
}