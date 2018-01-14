package redux

class Store<S>(reducer: Reducer<S>, /*preloadedState: S? = null,*/ enhancer: Any? = null) {
    var currentReducer = reducer
    private var currentState = reducer.initialState//preloadedState
    private var currentListeners: ArrayList<() -> Unit> = arrayListOf()
    private var nextListeners: ArrayList<() -> Unit> = currentListeners
    private var isDispatching: Boolean = false

    init{
        dispatch(Action(ActionTypes.INIT.name, currentState))
    }

    fun getState() = currentState

    fun ensureCanMutateNextListeners() {
        if (nextListeners == currentListeners) {
            nextListeners = currentListeners
        }
    }

    fun subscribe(listener: () -> Unit): () -> Unit {
        if (isDispatching) {
            throw Error("You may not call store.subscribe() while the reducer is executing. " +
                    "If you would like to be notified after the store has been updated, subscribe from a " +
                    "component and invoke store.getState() in the callback to access the latest state. " +
                    "See http://redux.js.org/docs/api/Store.html#subscribe for more details."
            )
        }

        var isSubscribed: Boolean = true
        ensureCanMutateNextListeners()
        nextListeners.add(listener)
        return fun() {
            if (!isSubscribed) {
                return
            }

            if (isDispatching) {
                throw Error("You may not unsubscribe from a store listener while the reducer is executing. " +
                        "See http://redux.js.org/docs/api/Store.html#subscribe for more details."
                )
            }

            isSubscribed = false

            ensureCanMutateNextListeners()
            val index = nextListeners.indexOf(listener)
            nextListeners.removeAt(index)
        }
    }

    fun dispatch(action: Action<S>): Action<S> {
        if (isDispatching) {
            throw Error("Reducers may not dispatch actions.")
        }

        try {
            isDispatching = true
            currentState = currentReducer.execute(currentState!!, action)
        } finally {
            isDispatching = false
        }

        currentListeners = nextListeners
        val listeners = currentListeners
        for (listener in listeners) {
            listener()
        }

        return action
    }
}