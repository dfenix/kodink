package examples.shoppingCart.reducers

import constants.ActionTypes
import redux.Action
import redux.State

data class InitialState(
        val addedIds: List<String> = listOf(),
        val quatityById: Map<String, Int> = mapOf()
) : State

data class ProductIDAction(override val type: String, val productId: String) : Action

fun addedIdsReducer(state: InitialState, action: ProductIDAction): InitialState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> {
            if (state.addedIds.contains(action.productId)) {
                state
            } else {
                state.copy(addedIds = state.addedIds.toList() + action.productId)
            }

        }
        else -> state
    }
}

fun quantityByIdReducer(state: InitialState, action: ProductIDAction): InitialState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> {
            val value = state.quatityById[action.productId]?.plus(1) ?: 0
            state.copy(quatityById = state.quatityById.toMap() + Pair(action.productId, value))
        }
        else -> state
    }
}

data class CartAction(override val type: String, val cart: InitialState) : Action

fun cartReducer(state: InitialState, action: CartAction): InitialState {
    return when (action.type) {
        ActionTypes.CHECKOUT_REQUEST -> InitialState()
        ActionTypes.CHECKOUT_FAILURE -> action.cart
        else -> {
            InitialState(
                    /*addedIds = addedIdsReducer(state, action).addedIds,
                    quatityById = quantityByIdReducer(state, action).quatityById*/
            )
        }
    }
}