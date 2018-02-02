package redux

typealias ReducerType<State> = (State, Action<State>) -> State
typealias ListenerType = () -> Unit

class Store2<State>{
    lateinit var currentReducer: ReducerType<State>
    val listReducers = mutableMapOf<String, ReducerType<State>>()
    private var currentState = mutableListOf<State>()
    private var listeners = mutableListOf<ListenerType>()
    private var isDispatching: Boolean = false

    fun getState() = currentState.last()
    fun getAllstates() = currentState

    fun addReducer(newReducer: ReducerType<State>): Boolean{
        val key = newReducer.javaClass.simpleName
        if(!listReducers.containsKey(key)){
            listReducers[key] = newReducer
            return true
        }
        return false
    }

    fun subscribe(listener: ListenerType){
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

    fun dispatch(action: Action<State>): Action<State> {
        if (isDispatching) {
            throw Error("Reducers may not dispatch actions.")
        }

        try {
            isDispatching = true
            //currentState.add(currentReducer(currentState.last(), action))

            for (reducer in listReducers) {
                val key = reducer.key
                val previousStateForKey = states[key]as State
                val nextStateForKey = reducer.value(previousStateForKey, action)
                nextState[key] = nextStateForKey
                hasChanged = hasChanged || nextStateForKey !== previousStateForKey
            }
            return if (hasChanged) MapOfStates(nextState) as State else state
        } finally {
            isDispatching = false
        }

        for (listener in listeners) {
            listener()
        }

        return action
    }
}