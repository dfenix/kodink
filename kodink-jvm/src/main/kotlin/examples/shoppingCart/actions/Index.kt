package examples.shoppingCart.actions

import constants.ActionTypes
import examples.shoppingCart.api.Product
import examples.shoppingCart.api.products
import examples.shoppingCart.containers.CartProduct
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import redux.Action
import redux.Provider
import redux.Provider.store

fun receiveProducts(products: List<Product>) = ActionWithProducts(ActionTypes.RECEIVE_PRODUCTS, products)

fun getAllproducts() {
    launch {
        delay(1000)
        Provider.store.dispatch(receiveProducts(products()))
    }
}

fun addToCartUnsafe(productId: Int): Action {
    return CartAction(ActionTypes.ADD_TO_CART, productId)
}

fun addToCart(productId: Int) {
    val state = Provider.store.getStateFor("products")
    if (state is ProductsState && state.byId[productId]?.inventory!! > 0) {
        Provider.store.dispatch(addToCartUnsafe(productId))
    }
}

fun checkout(products: List<CartProduct>) {
    store.dispatch(EmptyAction(ActionTypes.CHECKOUT_REQUEST))
    launch {
        delay(1000)
        store.dispatch(ActionWithCart(ActionTypes.CHECKOUT_SUCCESS, CartState()))
    }
}