package examples.shoppingCart.reducers.fromCart

import examples.shoppingCart.constants.AddToCart
import examples.shoppingCart.constants.CartState
import examples.shoppingCart.constants.CheckoutFailure
import examples.shoppingCart.constants.CheckoutRequest
import redux.Action
import redux.Reducer
import redux.State

class InitialState(
        val addedIds: List<Int> = listOf(),
        val quatityById: Map<Int, Int> = mapOf()
) : State

val addedIds = { state: List<Int>, action: Action ->
    when (action) {
        is AddToCart -> {
            if (state.contains(action.productId)) {
                state
            } else {
                state + action.productId
            }
        }
        else -> state
    }
}

val quantityById = { state: Map<Int, Int>, action: Action ->
    when (action) {
        is AddToCart -> {
            val productId = action.productId
            state + (productId to (state[productId]?.plus(1) ?: 0))
        }
        else -> state
    }
}


fun getQuantity(state: CartState, productId: Int) = state.quantityById[productId] ?: 0

fun getAddedIds(state: CartState) = state.addedIds

class Cart : Reducer({ state, action ->
    if (state is InitialState) {
        when (action) {
            is CheckoutRequest -> InitialState()
            is CheckoutFailure -> action.cart
            is AddToCart -> {
                InitialState(
                        addedIds(state.addedIds, action),
                        quantityById(state.quatityById, action)
                )
            }
            else -> state
        }
    } else {
        state
    }
})

