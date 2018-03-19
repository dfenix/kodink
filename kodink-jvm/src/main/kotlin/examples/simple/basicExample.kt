package examples.simple

import middleware.Logger
import redux.*
import redux.Provider.store

fun basicExample() {
    val reducer = Reducer { state, action ->
        if (state is IntState) {
            when (action) {
                is Inc -> IntState(state.value + action.payload)
                is Dec -> IntState(state.value - action.payload)
                else -> state
            }
        } else {
            state
        }
    }
    Provider.createStore(reducer, 0.toState())
    store.applyMiddleware(Logger())

    store.dispatch(Inc(1))
    store.dispatch(Inc(2))
    store.dispatch(Inc(22))
    store.dispatch(Inc(1))
    store.dispatch(Dec(1000))
}

fun multipleReducers() {
    val user = Reducer { state, action ->
        if (state is UserState) {
            when (action) {
                is ChangeName -> state.copy(name = action.name)
                is ChangeAge -> state.copy(age = action.age)
                else -> state
            }
        } else {
            state
        }
    }

    val tweets = Reducer { state, action ->
        if (state is TweetsState) {
            when (action) {
                is AddText -> state.copy(state.tweets + "new text")
                else -> state
            }
        } else {
            state
        }
    }

    val combine = combineReducers("user" to user, "tweets" to tweets)
    /*val state = States()
    state["user"] = UserState()
    state["tweets"] = TweetsState()
    Provider.createStore(combine, state)
    store.dispatch(ChangeName("David"))
    store.dispatch(ChangeAge(25))
    store.dispatch(ChangeAge(26))

    println(store.getState())*/
//    TODO println(store.getStateFor("user"))
//    TODO println(store.getStateFor("tweets"))
}

data class Inc(val payload: Int) : Action
data class Dec(val payload: Int) : Action

data class UserState(val name: String = "", val age: Int = 0) : State
data class ChangeName(val name: String) : Action
data class ChangeAge(val age: Int) : Action

data class TweetsState(val tweets: List<String> = listOf()) : State
class AddText : Action

