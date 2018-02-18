package examples.shoppingCart.reducers

import examples.shoppingCart.constants.AddToCart
import examples.shoppingCart.constants.CartState
import examples.shoppingCart.constants.CheckoutFailure
import examples.shoppingCart.constants.CheckoutRequest
import redux.Action
import redux.Provider.store

fun cartReducer() {
    store.addReducer(::cart, "cart", CartState())
}

fun cart(state: CartState, action: Action): CartState {
    return when (action) {
        is CheckoutRequest -> CartState()
        is CheckoutFailure -> action.cart
        is AddToCart -> {
            CartState(
                    addedIds = addedIds(CartState(state.addedIds), action).addedIds,
                    quatityById = quantityById(CartState(quatityById = state.quatityById), action).quatityById
            )
        }
        else -> state
    }
}

fun addedIds(state: CartState, action: Action): CartState {
    return when (action) {
        is AddToCart -> {
            if (state.addedIds.contains(action.productId)) {
                state
            } else {
                CartState(addedIds = state.addedIds.toList() + action.productId)
            }
        }
        else -> state
    }
}

fun quantityById(state: CartState, action: Action): CartState {
    return when (action) {
        is AddToCart -> {
            val value = state.quatityById[action.productId] ?: 0
            CartState(quatityById = state.quatityById.toMap() + Pair(action.productId, value + 1))
        }
        else -> state
    }
}


fun getQuantity(state: CartState, productId: Int) = state.quatityById[productId] ?: 0

