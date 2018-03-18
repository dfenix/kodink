package redux

//typealias ReducerType<State, Action> = (State, Action) -> State
typealias ListenerType = () -> Unit
//typealias Middleware = (Action) -> Action

class Store(val reducer: IReducer, initialState: Any) {
    //    private val listReducers = mutableMapOf<String, ReducerType<State, Action>>()
    private var currentState = initialState
    private var listeners = mutableListOf<ListenerType>()
    private var isDispatching: Boolean = false
    private var middlewares = mutableListOf<Middleware>()
    private val hasMiddleware
        get() = middlewares.size > 0

    fun getState() = currentState

//    fun getStateFor(name: String) = (currentState as States)[name]!!

    /*fun <S : State, A : Action> addReducer(newReducer: ReducerType<S, A>, name: String, initialState: S): Boolean {
        val key = if (name.isEmpty()) "stored_fun_${listReducers.size}" else name
        if (!listReducers.containsKey(key)) {
            @Suppress("UNCHECKED_CAST")
            listReducers[key] = newReducer as ReducerType<State, Action>
            currentState[key] = initialState
            return true
        }
        return false
    }*/

    fun subscribe(listener: ListenerType): () -> Unit {
        if (isDispatching) {
            throw Error("You may not call store.subscribe() while the reducer is executing. " +
                    "If you would like to be notified after the store has been updated, subscribe from a " +
                    "component and invoke store.getState() in the callback to access the latest state. " +
                    "See http://redux.js.org/docs/api/Store.html#subscribe for more details."
            )
        }
        listeners.add(listener)
//        val index = listeners.size
        return fun() {
            if (isDispatching) {
                throw Error("You may not unsubscribe from a store listener while the reducer is executing.")
            }
//            listeners.removeAt(index)
            listeners.remove(listener)
        }
    }

    fun dispatch(action: Action) {
        if (!hasMiddleware) {
            dispatchToReducer(action)
        } else {
            var newAction = action
            middlewares.map { newAction = it.beforeDispatch(newAction) }
            dispatchToReducer(newAction)
        }
    }

    private fun dispatchToReducer(action: Action): Action {
        if (isDispatching) {
            throw Error("Reducers may not dispatch actions.")
        }

        try {
            isDispatching = true
            currentState = reducer.reduce(currentState, action)
        } finally {
            isDispatching = false
        }

        for (listener in listeners) {
            listener()
        }

        return action
    }

    fun applyMiddleware(vararg middleware: Middleware) {
        middlewares.addAll(middleware)
    }
}
