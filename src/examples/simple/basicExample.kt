package examples.simple

import redux.Action
import redux.Provider.store
import redux.State

fun basicExample() {
    /* Simple implementation */
    fun reducer(state: IntState, action: Action<IntState>): IntState {
        return when (action.type) {
            "INC" -> IntState(state.num + action.payload.num)
            "DEC" -> IntState(state.num - action.payload.num)
            else -> state
        }
    }

    store.addReducer(::reducer, IntState())
    store.subscribe { println("Store changed: ${store.getState()}") }

    store.dispatch(Action("INC", IntState(1)))
    store.dispatch(Action("INC", IntState(2)))
    store.dispatch(Action("INC", IntState(22)))
    store.dispatch(Action("INC", IntState(1)))
    store.dispatch(Action("DEC", IntState(1000)))
    /* Simple implementation */

    /* Multiple reducers */
    fun userReducer(state: UserState, action: Action<UserState>): UserState {
        return when (action.type) {
            "CHANGE_NAME" -> state.copy(name = action.payload.name) //state = {...state, name: action.payload}
            "CHANGE_AGE" -> state.copy(age = action.payload.age)
            else -> state
        }
    }

    store.addReducer(::userReducer, UserState())

    fun tweetsReducer(state: TweetsState, action: Action<TweetsState>): TweetsState {
        return when (action.type) {
            "ADD_TEXT" -> state.copy(tweets = action.payload.tweets)
            else -> state
        }
    }

    store.addReducer(::tweetsReducer, TweetsState())

    store.subscribe { println("Store changed: ${store.getState()}") }

    store.dispatch(Action("CHANGE_NAME", UserState("David")))
    store.dispatch(Action("CHANGE_AGE", UserState(age = 25)))
    store.dispatch(Action("CHANGE_AGE", UserState(age = 26)))
    /* Multiple reducers */
}

data class IntState(var num: Int = 0) : State
data class UserState(var name: String = "", var age: Int = 0) : State
data class TweetsState(var tweets: List<String> = listOf()) : State

