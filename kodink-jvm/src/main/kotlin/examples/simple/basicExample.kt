package examples.simple

import logger
import redux.Action
import redux.Provider.store
import redux.State

fun basicExample() {
    /* Simple implementation */
    fun reducer(state: IntState, action: Action): IntState {
        return when (action) {
            is Inc -> state.copy(num = state.num + action.payload)
            is Dec -> state.copy(num = state.num - action.payload)
            else -> state
        }
    }

    store.addReducer(::reducer, "reducer", IntState())
    store.applyMiddleware(::logger)

    store.dispatch(Inc(1))
    store.dispatch(Inc(2))
    store.dispatch(Inc(22))
    store.dispatch(Inc(1))
    store.dispatch(Dec(1000))
    /* Simple implementation */

    /* Multiple reducers */
    fun userReducer(state: UserState, action: Action): UserState {
        return when (action) {
            is ChangeName -> state.copy(name = action.name)
            is ChangeAge -> state.copy(age = action.age)
            else -> state
        }
    }

    store.addReducer(::userReducer, "user", UserState())

    fun tweetsReducer(state: TweetsState, action: Action): TweetsState {
        return when (action) {
            is AddText -> state.copy()
            else -> state
        }
    }

    store.addReducer(::tweetsReducer, "tweets", TweetsState())

    store.dispatch(ChangeName("David"))
    store.dispatch(ChangeAge(25))
    store.dispatch(ChangeAge(26))

    println(store.getState())
    /* Multiple reducers */
}

data class IntState(var num: Int = 0) : State
data class Inc(val payload: Int) : Action
data class Dec(val payload: Int) : Action

data class UserState(var name: String = "", var age: Int = 0) : State
data class ChangeName(val name: String) : Action
data class ChangeAge(val age: Int) : Action

data class TweetsState(var tweets: List<String> = listOf()) : State
class AddText : Action


