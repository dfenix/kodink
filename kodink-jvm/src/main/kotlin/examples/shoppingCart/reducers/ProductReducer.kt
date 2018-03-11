package examples.shoppingCart.reducers

import examples.shoppingCart.api.Product
import examples.shoppingCart.constants.AddToCart
import examples.shoppingCart.constants.AppState
import examples.shoppingCart.constants.ReceiveProducts
import redux.Action

//data class ProductsState(val inventory: Int = 0) : State

fun productsReducer() {
//    TODO store.addReducer(::combineProductsReducer, "products", AppState())
}

fun combineProductsReducer(state: AppState, action: Action): AppState {
    val byIdResult = byId(state, action)
    val visibleIdsResult = visibleIds(state, action)
    return state.copy(byId = byIdResult.byId, visibleIds = visibleIdsResult.visibleIds)
}

fun products(state: AppState, action: Action): AppState {
    return when (action) {
        is AddToCart -> {
            val product = state.byId[action.productId]!!.apply {
                copy(inventory = inventory.minus(1))
            }
            val idProduct = mapOf(action.productId to product)
            AppState(byId = idProduct)
        }
    //ProductState(state.product.copy(inventory = state.product.inventory.minus(1)))
        else -> state
    }
}

fun byId(state: AppState, action: Action): AppState {
    return when (action) {
        is ReceiveProducts -> {
            val reduce = mutableMapOf<Int, Product>()
            state.copy(byId = action.products.fold(reduce) { acc, product ->
                acc[product.id] = product
                acc
            })
        }
        is AddToCart -> {
            state.copy(byId = state.byId + products(state, action).byId)
        }
        else -> state
    }
}

fun visibleIds(state: AppState, action: Action): AppState {
    return when (action) {
        is ReceiveProducts -> AppState(visibleIds = action.products.map { it.id })
        else -> state
    }
}

fun getProduct(state: AppState, id: Int) = state.byId[id]!!

fun getVisibleProducts(state: AppState) = state.visibleIds.map {
    getProduct(state, it)
}