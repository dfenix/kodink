package examples.shoppingCart.actions

import constants.ActionTypes
import redux.Action
import redux.Provider.store

data class AddToCartUnsafeAction(override val type: String, val productId: String) : Action

fun addToCartUnsafe(productId: String): Action {
    return AddToCartUnsafeAction(ActionTypes.ADD_TO_CART, productId)
}

fun addToCart(productId: String) {
    if (store.getStateFor("products") != null) {
        store.dispatch(addToCartUnsafe(productId))
    }
}