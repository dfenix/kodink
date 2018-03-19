package redux

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

class Combination(val reducers: Map<String, IReducer>) : IReducer {
    override val reduce = { state: State, action: Action ->
        //        var members = state::class.members
        var hasChanged = false
        val nextState = factory(state::class)
        for (reducer in reducers) {
            val key = reducer.key
            val previousStateForKey = getMemberValue(state::class, key) //state[key]!!
            println(state[key])
            /*try {
                var nextStateForKey = reducer.value.reduce(previousStateForKey, action)
                nextState[key] = nextStateForKey
                hasChanged = hasChanged || nextStateForKey !== previousStateForKey
            } catch (e: Exception) {
                nextState[key] = previousStateForKey
                println(e)
            }*/
        }
        if (hasChanged) {
            nextState
        } else {
            state
        }
        state

    }

    fun factory(state: KClass<out State>): State{
        return state.createInstance()
    }

    fun getMemberValue(state: KClass<out State>, name: String) = state.memberProperties.find {
        it.name == name
    }
}


