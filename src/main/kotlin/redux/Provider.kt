package redux

object Provider {
    lateinit var store: Store
    fun createStore(reducer: IReducer, initialState: Any) {
        this.store = Store(reducer, initialState)
    }
}