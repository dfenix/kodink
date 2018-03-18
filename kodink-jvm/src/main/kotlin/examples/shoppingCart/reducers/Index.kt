package examples.shoppingCart.reducers

import examples.shoppingCart.constants.AppState
import examples.shoppingCart.reducers.fromCart.Cart
import examples.shoppingCart.reducers.fromCart.getAddedIds
import examples.shoppingCart.reducers.fromCart.getQuantity
import redux.States
import redux.combineReducers

fun reducer() = combineReducers("cart" to Cart(), "products" to combineProductsReducer())

fun getAddedIds(state: AppState) = getAddedIds(state.cart)
fun getQuantity(state: AppState, id: Int) = getQuantity(state.cart, id)
fun getProduct(state: AppState, id: Int) = getProduct(state.products, id)

fun getTotal(state: AppState) = getAddedIds(state).fold(0.0) { total, id -> total + getProduct(state, id).price * getQuantity(state, id) }

fun getCartProducts(state: States) = getAddedIds(state).map {
    getProduct(state, it).copy(quantity = getQuantity(state, it))
}


