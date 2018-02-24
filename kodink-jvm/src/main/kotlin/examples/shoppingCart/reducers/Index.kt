package examples.shoppingCart.reducers

import examples.shoppingCart.constants.AppState
import examples.shoppingCart.containers.CartProduct
import redux.State

fun combineReducers() {
    cartReducer()
    productsReducer()
}

fun getAddedIds(state: AppState) = state.addedIds

fun getCartProducts(state: MutableMap<String, State>) = getAddedIds(state["cart"] as AppState).map {
    val product = getProduct(state["products"] as AppState, it)
    CartProduct().apply {
        id = product.id
        title = product.title
        price = product.price
//        getProduct(state["product"] as ProductsState, it)
        quantity = getQuantity(state["cart"] as AppState, it)
    }
}

fun getTotal(state: MutableMap<String, State>) = getAddedIds(state["cart"] as AppState).fold(0.0) { total, id ->
    total + getProduct(state["products"] as AppState, id).price * getQuantity(state["cart"] as AppState, id)
}


