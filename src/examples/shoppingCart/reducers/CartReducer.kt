package examples.shoppingCart.reducers

import constants.ActionTypes
import redux.Action
import redux.State

data class InitialState(
        val addedIds: List<String> = listOf(),
        val quatityById: Map<String, Int> = mapOf(),
        val productId: String = "",
        val cart: String = ""
) : State

fun addedIdsReducer(state: InitialState, action: Action<InitialState>): InitialState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> {
            if (state.addedIds.contains(action.payload.productId)) {
                state
            } else {
                state.copy(addedIds = state.addedIds.toList() + action.payload.productId)
            }

        }
        else -> state
    }
}

fun quantityByIdReducer(state: InitialState, action: Action<InitialState>): InitialState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> {
            val value = state.quatityById[action.payload.productId]?.plus(1) ?: 0
            state.copy(quatityById = state.quatityById.toMap() + Pair(action.payload.productId, value))
        }
        else -> state
    }
}

fun cartReducer(state: InitialState, action: Action<InitialState>): InitialState {
    return when (action.type) {
        ActionTypes.CHECKOUT_REQUEST -> InitialState()
        ActionTypes.CHECKOUT_FAILURE -> InitialState(cart = action.payload.cart)
        else -> {
            InitialState(
                    addedIds = addedIdsReducer(state, action).addedIds,
                    quatityById = quantityByIdReducer(state, action).quatityById
            )
        }
    }
}