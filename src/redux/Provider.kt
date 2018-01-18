package redux

object Provider{
    lateinit var store: Store<State>
    fun <S>createStore(reducer: Reducer<S>){
        store = Store(reducer) as Store<State>
    }
}