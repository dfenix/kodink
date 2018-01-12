import redux.Action
import redux.Reducer
import redux.Store
import redux.State
import redux.combineReducers

fun main(args: Array<String>) {
    /* Simple implementation */
    val reducer = { state: Int, action: Action<Int> ->
        when (action.type) {
            "INC" -> state + action.value
            "DEC" -> state - action.value
            else -> state
        }
    }

    val store = Store(reducer, 0)

    store.subscribe { println("Store changed: ${store.getState()}") }

    store.dispatch(Action("INC", 1))
    store.dispatch(Action("INC", 2))
    store.dispatch(Action("INC", 22))
    store.dispatch(Action("INC", 1))
    store.dispatch(Action("DEC", 1000))
    /* Simple implementation */

    /* Multiple reducers */
    class User(var name: String = "", var age: Int = 0)

    val userReducer = Reducer()
    userReducer.subscribe{ state: UserState, action: Action<UserState> ->
        when (action.type) {
            "CHANGE_NAME" -> UserState(action.value.name) //state = {...state, name: action.payload}
            "CHANGE_AGE" -> UserState(age = action.value.age)
            else -> state
        }
    }

    val tweetsReducer = Reducer()
    tweetsReducer.subscribe { state: StringState, action: Action<StringState> ->
        when (action.type) {
            "ADD_TEXT" -> action.value
            else -> state
        }
    }

    val reducers: List<out Reducer> = listOf(userReducer, tweetsReducer)
    /*val reducers = combineReducers(userReducer, tweetsReducer)
    val storee = Store(reducers)
    storee.subscribe { println("Store changed: ${store.getState()}") }

    storee.dispatch(Action("CHANGE_NAME", User("David")))
    storee.dispatch(Action("CHANGE_AGE", User(age = 25)))
    storee.dispatch(Action("CHANGE_AGE", User(age = 26)))*/
    /* Multiple reducers */
}

class IntState(var num: Int): State
class StringState(var txt: String): State
class UserState(var name: String = "", var age: Int = 0): State
class TweetsState(var tweets: List<String>): State

