package examples.simple

import redux.*
import redux.Provider.store
import middleware.Logger

fun basicExample() {
    /* Simple implementation */
    val reducer = Reducer{ state: Any, action: Action ->
        return@Reducer if(state is Int) {
            when (action) {
                is Inc -> state + action.payload
                is Dec -> state - action.payload
                else -> state
            }
        }else state
    }
    Provider.createStore(reducer, 0)
    store.applyMiddleware(Logger())

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

    //TODO store.addReducer(::userReducer, "user", UserState())

    fun tweetsReducer(state: TweetsState, action: Action): TweetsState {
        return when (action) {
            is AddText -> state.copy()
            else -> state
        }
    }

    //TODO store.addReducer(::tweetsReducer, "tweets", TweetsState())

    store.dispatch(ChangeName("David"))
    store.dispatch(ChangeAge(25))
    store.dispatch(ChangeAge(26))

    println(store.getState())
    /* Multiple reducers */
}

class IntState(val num: Int = 0): Object()
data class Inc(val payload: Int) : Action
data class Dec(val payload: Int) : Action

data class UserState(val name: String = "", val age: Int = 0)//TODO : State
data class ChangeName(val name: String) : Action
data class ChangeAge(val age: Int) : Action

data class TweetsState(val tweets: List<String> = listOf())//TODO : State
class AddText : Action


