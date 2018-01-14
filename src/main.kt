import redux.*

fun main(args: Array<String>) {
    /* Simple implementation */
    val reducer = Reducer({ state: IntState, action: Action<IntState> ->
        when (action.type) {
            "INC" -> IntState(state.num + action.value.num)
            "DEC" -> IntState(state.num - action.value.num)
            else -> state
        }
    })
    reducer.execute(IntState(0),Action("INC", IntState(2)))

    val store = Store(reducer, IntState(0))

    store.subscribe { println("Store changed: ${store.getState()?.num}") }

    store.dispatch(Action("INC", IntState(1)))
    store.dispatch(Action("INC", IntState(2)))
    store.dispatch(Action("INC", IntState(22)))
    store.dispatch(Action("INC", IntState(1)))
    store.dispatch(Action("DEC", IntState(1000)))
    /* Simple implementation */

    /* Multiple reducers */
    class User(var name: String = "", var age: Int = 0)

    val userReducer = Reducer({ state: UserState, action: Action<UserState> ->
        when (action.type) {
            "CHANGE_NAME" -> UserState(action.value.name) //state = {...state, name: action.payload}
            "CHANGE_AGE" -> UserState(age = action.value.age)
            else -> state
        }
    })

    val tweetsReducer = Reducer({ state: StringState, action: Action<StringState> ->
        when (action.type) {
            "ADD_TEXT" -> action.value
            else -> state
        }
    })

    val reducers = combineReducers(Pair("user", userReducer), Pair("tweets", tweetsReducer))
    val store2 = Store(reducers)
    store2.subscribe { println("Store changed: ${store.getState()}") }

    store2.dispatch(Action("CHANGE_NAME", UserState("David")))
    store2.dispatch(Action("CHANGE_AGE", UserState(age = 25)))
    store2.dispatch(Action("CHANGE_AGE", UserState(age = 26)))
    /* Multiple reducers */
}

class IntState(var num: Int): State
class StringState(var txt: String): State
class UserState(var name: String = "", var age: Int = 0): State
class TweetsState(var tweets: List<String>): State

