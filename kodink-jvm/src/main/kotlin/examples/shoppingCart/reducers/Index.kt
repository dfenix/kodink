package examples.shoppingCart.reducers

import examples.shoppingCart.constants.CartState
import examples.shoppingCart.constants.ProductsState
import examples.shoppingCart.containers.CartProduct
import redux.State

fun combineReducers() {
    cartReducer()
    productsReducer()
}

fun getAddedIds(state: CartState) = state.addedIds

fun getCartProducts(state: MutableMap<String, State>) = getAddedIds(state["cart"] as CartState).map {
    val product = getProduct(state["products"] as ProductsState, it)
    CartProduct().apply {
        id = product.id
        title = product.title
        price = product.price
//        getProduct(state["product"] as ProductsState, it)
        quantity = getQuantity(state["cart"] as CartState, it)
    }
}

fun getTotal(state: MutableMap<String, State>) = getAddedIds(state["cart"] as CartState).fold(0.0) { total, id ->
    total + getProduct(state["products"] as ProductsState, id).price * getQuantity(state["cart"] as CartState, id)
}


