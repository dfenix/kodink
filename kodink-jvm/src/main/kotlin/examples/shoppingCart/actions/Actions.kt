package examples.shoppingCart.actions

import constants.ActionTypes
import examples.shoppingCart.api.Product
import examples.shoppingCart.api.products
import redux.Action
import redux.Provider.store

class ActionWithProducts(override val type: String, val products: List<Product>) : Action

fun receiveProducts(products: List<Product>) = ActionWithProducts(ActionTypes.RECEIVE_PRODUCTS, products)

fun getAllproducts() {
    store.dispatch(receiveProducts(products()))
}

data class AddToCartUnsafeAction(override val type: String, val productId: String) : Action

fun addToCartUnsafe(productId: String): Action {
    return AddToCartUnsafeAction(ActionTypes.ADD_TO_CART, productId)
}

fun addToCart(productId: String) {
    if (store.getStateFor("products") != null) {
        store.dispatch(addToCartUnsafe(productId))
    }
}