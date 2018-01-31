package examples.simple

import redux.*

fun basicExample() {
    /* Simple implementation */
    val reducer = fun (state: IntState, action: Action<IntState>):IntState{
        return when (action.type) {
            "INC" -> IntState(state.num + action.payload.num)
            "DEC" -> IntState(state.num - action.payload.num)
            else -> state
        }
    }

    val store = Store(reducer, IntState(0))
    store.subscribe { println("Store changed: ${store.getState()}") }

    store.dispatch(Action("INC", IntState(1)))
    store.dispatch(Action("INC", IntState(2)))
    store.dispatch(Action("INC", IntState(22)))
    store.dispatch(Action("INC", IntState(1)))
    store.dispatch(Action("DEC", IntState(1000)))
    /* Simple implementation */

    /* Multiple reducers */
    val userReducer = Reducer(UserState(), { state: UserState, action: Action<UserState> ->
        when (action.type) {
            "CHANGE_NAME" -> UserState(action.payload.name) //state = {...state, name: action.payload}
            "CHANGE_AGE" -> UserState(age = action.payload.age)
            else -> state
        }
    })

    val tweetsReducer = Reducer(TweetsState(), { state: TweetsState, action: Action<TweetsState> ->
        when (action.type) {
            "ADD_TEXT" -> action.payload
            else -> state
        }
    })

    val reducers = superReducer2("user" to userReducer, "tweets" to tweetsReducer)
    val store2 = Store(reducers)
    store2.subscribe { println("Store changed: ${store2.getState()}") }

    store2.dispatch(Action("CHANGE_NAME", UserState("David")))
    store2.dispatch(Action("CHANGE_AGE", UserState(age = 25)))
    store2.dispatch(Action("CHANGE_AGE", UserState(age = 26)))
    /* Multiple reducers */
}



data class IntState(var num: Int): State
data class StringState(var txt: String): State
data class UserState(var name: String = "", var age: Int = 0): State
data class TweetsState(var tweets: List<String> = listOf()): State

