package examples.simple

import logger
import redux.Action
import redux.Provider.store
import redux.State

fun basicExample() {
    /* Simple implementation */
    fun reducer(state: IntState, action: IntAction): IntState {
        return when (action.type) {
            "INC" -> state.copy(num = state.num + action.payload)
            "DEC" -> state.copy(num = state.num - action.payload)
            else -> state
        }
    }

    store.addReducer(::reducer, IntState())
    store.applyMiddleware(::logger)

    store.dispatch(IntAction("INC", 1))
    store.dispatch(IntAction("INC", 2))
    store.dispatch(IntAction("INC", 22))
    store.dispatch(IntAction("INC", 1))
    store.dispatch(IntAction("DEC", 1000))
    /* Simple implementation */

    /* Multiple reducers */
    fun userReducer(state: UserState, action: UserAction): UserState {
        return when (action.type) {
            "CHANGE_NAME" -> state.copy(name = action.payload.name) //state = {...state, name: action.payload}
            "CHANGE_AGE" -> state.copy(age = action.payload.age)
            else -> state
        }
    }

    store.addReducer(::userReducer, UserState())

    fun tweetsReducer(state: TweetsState, action: Action): TweetsState {
        return when (action.type) {
            "ADD_TEXT" -> state.copy()
            else -> state
        }
    }

    store.addReducer(::tweetsReducer, TweetsState())

    store.dispatch(UserAction("CHANGE_NAME", UserState("David")))
    store.dispatch(UserAction("CHANGE_AGE", UserState(age = 25)))
    store.dispatch(UserAction("CHANGE_AGE", UserState(age = 26)))

    println(store.getState())
    /* Multiple reducers */
}

data class IntState(var num: Int = 0) : State
data class IntAction(override val type: String, val payload: Int) : Action

data class UserState(var name: String = "", var age: Int = 0) : State
data class UserAction(override val type: String, val payload: UserState) : Action

data class TweetsState(var tweets: List<String> = listOf()) : State


