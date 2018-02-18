package examples.shoppingCart.reducers

import constants.ActionTypes
import redux.Action
import redux.Provider.store
import redux.State

fun cartReducer(){
    store.addReducer(::cart, "cart", CartState())
}

data class CartState(
        val addedIds: List<Int> = listOf(),
        val quatityById: Map<Int, Int> = mapOf()
) : State

fun cart(state: CartState, action: CartAction): CartState {
    return when (action.type) {
        ActionTypes.CHECKOUT_REQUEST -> CartState()
        ActionTypes.CHECKOUT_FAILURE -> action.cart//TODO check this
        else -> {
            val action2 = ProductIDAction("", 0)
            CartState(
                    addedIds = addedIds(CartState(state.addedIds), action2/*action*/).addedIds,
                    quatityById = quantityById(CartState(quatityById = state.quatityById), action2 /*action*/).quatityById
            )
        }
    }
}

data class ProductIDAction(override val type: String, val productId: Int) : Action

fun addedIds(state: CartState, action: ProductIDAction): CartState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> {
            if (state.addedIds.contains(action.productId)) {
                state
            } else {
                CartState(addedIds = state.addedIds.toList() + action.productId)
            }

        }
        else -> state
    }
}

fun quantityById(state: CartState, action: ProductIDAction): CartState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> {
            val value = state.quatityById[action.productId]?.plus(1) ?: 0
            state.copy(quatityById = state.quatityById.toMap() + Pair(action.productId, value))
        }
        else -> state
    }
}

data class CartAction(override val type: String, val cart: CartState) : Action

fun getQuantity(state: CartState, productId: Int) = state.quatityById[productId] ?: 0

