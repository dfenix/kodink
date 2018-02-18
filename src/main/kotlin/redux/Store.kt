package redux

typealias ReducerType<State, Action> = (State, Action) -> State
typealias ListenerType = () -> Unit
typealias Middleware = (Action) -> Action

class Store {
    private val listReducers = mutableMapOf<String, ReducerType<State, Action>>()
    private var currentState = mutableMapOf<String, State>()
    private var listeners = mutableListOf<ListenerType>()
    private var isDispatching: Boolean = false
    private var middlewares = mutableListOf<Middleware>()

    fun getState() = currentState
    fun getStateFor(name: String) = currentState[name]!!

    fun <S : State, A : Action> addReducer(newReducer: ReducerType<S, A>, name: String, initialState: S): Boolean {
        val key = if (name.isEmpty()) "stored_fun_${listReducers.size}" else name
        if (!listReducers.containsKey(key)) {
            @Suppress("UNCHECKED_CAST")
            listReducers[key] = newReducer as ReducerType<State, Action>
            currentState[key] = initialState
            return true
        }
        return false
    }

    fun subscribe(listener: ListenerType) {
        if (isDispatching) {
            throw Error("You may not call store.subscribe() while the reducer is executing. " +
                    "If you would like to be notified after the store has been updated, subscribe from a " +
                    "component and invoke store.getState() in the callback to access the latest state. " +
                    "See http://redux.js.org/docs/api/Store.html#subscribe for more details."
            )
        }
        listeners.add(listener)
    }

    fun unsubscribe() {
        if (isDispatching) {
            throw Error("You may not unsubscribe from a store listener while the reducer is executing. " +
                    "See http://redux.js.org/docs/api/Store.html#subscribe for more details."
            )
        }
//        val index = nextListeners.indexOf(listener)
//        nextListeners.removeAt(index)
    }

    fun dispatch(action: Action) {
        var newAction = action
        if (middlewares.size == 0) {
            _dispatch(action)
        } else {
            middlewares.map { newAction = it(newAction) }
            _dispatch(newAction)
        }
    }

    private fun _dispatch(action: Action): Action {
        if (isDispatching) {
            throw Error("Reducers may not dispatch actions.")
        }

        try {
            isDispatching = true

            var hasChanged = false
            val nextState = mutableMapOf<String, State>()
            for (reducer in listReducers) {
                val key = reducer.key
                val previousStateForKey = currentState[key]!!
                try {
                    val nextStateForKey = reducer.value(previousStateForKey, action)
                    nextState[key] = nextStateForKey
                    hasChanged = hasChanged || nextStateForKey !== previousStateForKey
                } catch (e: Exception) {
                    nextState[key] = previousStateForKey
                }
            }
            if (hasChanged) currentState = nextState
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
