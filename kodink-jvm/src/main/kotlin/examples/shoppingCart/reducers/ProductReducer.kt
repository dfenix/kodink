package examples.shoppingCart.reducers

import examples.shoppingCart.api.Product
import examples.shoppingCart.constants.AddToCart
import examples.shoppingCart.constants.Products
import examples.shoppingCart.constants.ReceiveProducts
import redux.*

fun products(state: Product, action: Action): Product {
    return when (action) {
        is AddToCart -> {
            state.copy(inventory = state.inventory.minus(1))
        }
        else -> state
    }
}

val byId = Reducer({ state, action: Action ->
    if (state is MapState) {
        when (action) {
            is ReceiveProducts -> {
                val acc = mutableMapOf<Int, Product>()
                MapState(state.value + action.products.fold(acc) { acc, product ->
                    acc[product.id] = product
                    acc
                })
            }
            is AddToCart -> {
                val productId = action.productId
                MapState(state.value + (productId to products(state.value[productId] as Product, action)))
            }
            else -> state
        }
    } else {
        state
    }
})

val visibleIds = Reducer({ state, action ->
    if (state is ListState) {
        when (action) {
            is ReceiveProducts -> ListState(action.products.map { it.id })
            else -> state
        }
    } else {
        state
    }
})

fun combineProductsReducer() = combineReducers("byId" to byId, "visibleIds" to visibleIds)

fun getProduct(state: Products, id: Int) = state.byId[id]!!

fun getVisibleProducts(state: Products) = state.visibleIds.map {
    getProduct(state, it)
}