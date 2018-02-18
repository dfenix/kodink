package examples.shoppingCart.reducers

import constants.ActionTypes
import examples.shoppingCart.actions.ActionWithCart
import examples.shoppingCart.actions.CartAction
import examples.shoppingCart.actions.CartState
import examples.shoppingCart.actions.EmptyAction
import redux.Action
import redux.Provider.store

fun cartReducer() {
    store.addReducer(::cart, "cart", CartState())
}

fun cart(state: CartState, action: Action): CartState {
    return when {
        action.type == ActionTypes.CHECKOUT_REQUEST && action is EmptyAction -> CartState()
        action.type == ActionTypes.CHECKOUT_FAILURE && action is ActionWithCart -> action.cart
        action is CartAction -> {
            CartState(
                    addedIds = addedIds(CartState(state.addedIds), action).addedIds,
                    quatityById = quantityById(CartState(quatityById = state.quatityById), action).quatityById
            )
        }
        else -> state
    }
}

fun addedIds(state: CartState, action: CartAction): CartState {
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

fun quantityById(state: CartState, action: CartAction): CartState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> {
            val value = state.quatityById[action.productId] ?: 0
            CartState(quatityById = state.quatityById.toMap() + Pair(action.productId, value + 1))
        }
        else -> state
    }
}


fun getQuantity(state: CartState, productId: Int) = state.quatityById[productId] ?: 0

