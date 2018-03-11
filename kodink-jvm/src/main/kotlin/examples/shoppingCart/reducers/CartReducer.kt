package examples.shoppingCart.reducers

import examples.shoppingCart.constants.AddToCart
import examples.shoppingCart.constants.AppState
import examples.shoppingCart.constants.CheckoutFailure
import examples.shoppingCart.constants.CheckoutRequest
import redux.Action

fun cartReducer() {
    //TODO store.addReducer(::cart, "cart", AppState())
}

fun cart(state: AppState, action: Action): AppState {
    return when (action) {
        is CheckoutRequest -> AppState()
        is CheckoutFailure -> action.cart
        is AddToCart -> {
            AppState(
                    addedIds = addedIds(AppState(state.addedIds), action).addedIds,
                    quatityById = quantityById(AppState(quatityById = state.quatityById), action).quatityById
            )
        }
        else -> state
    }
}

fun addedIds(state: AppState, action: Action): AppState {
    return when (action) {
        is AddToCart -> {
            if (state.addedIds.contains(action.productId)) {
                state
            } else {
                AppState(addedIds = state.addedIds.toList() + action.productId)
            }
        }
        else -> state
    }
}

fun quantityById(state: AppState, action: Action): AppState {
    return when (action) {
        is AddToCart -> {
            val value = state.quatityById[action.productId] ?: 0
            AppState(quatityById = state.quatityById.toMap() + Pair(action.productId, value + 1))
        }
        else -> state
    }
}


fun getQuantity(state: AppState, productId: Int) = state.quatityById[productId] ?: 0

