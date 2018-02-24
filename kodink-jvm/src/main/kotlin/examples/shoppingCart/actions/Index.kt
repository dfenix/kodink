package examples.shoppingCart.actions

import examples.shoppingCart.api.Product
import examples.shoppingCart.api.products
import examples.shoppingCart.constants.*
import examples.shoppingCart.containers.CartProduct
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import redux.Action
import redux.Provider
import redux.Provider.store

fun receiveProducts(products: List<Product>) = ReceiveProducts(products)

fun getAllproducts() {
    launch {
        delay(1000)
        Provider.store.dispatch(receiveProducts(products()))
    }
}

fun addToCartUnsafe(productId: Int): Action {
    return AddToCart(productId)
}

fun addToCart(productId: Int) {
    val state = Provider.store.getState() as AppState
    if (state.byId[productId]?.inventory!! > 0) {
        Provider.store.dispatch(addToCartUnsafe(productId))
    }
}

fun checkout(products: List<CartProduct>) {
    store.dispatch(CheckoutRequest())
    launch {
        delay(1000)
        store.dispatch(CheckoutSuccess(AppState()))
    }
}