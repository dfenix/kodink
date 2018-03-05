package redux

object Provider {
    lateinit var store: Store
    fun createStore(reducer: Reducer, initialState: Any){
        this.store = Store(reducer, initialState)
    }
}