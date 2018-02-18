package examples.shoppingCart.reducers

import examples.shoppingCart.api.Product
import examples.shoppingCart.constants.*
import redux.Action
import redux.Provider.store

//data class ProductsState(val inventory: Int = 0) : State

fun productsReducer() {
    store.addReducer(::combineProductsReducer, "products", ProductsState())
}

fun combineProductsReducer(state: ProductsState, action: Action): ProductsState {
    val idState = byId(ByIdState(state.byId), action)
    val visibleState = visibleIds(ListState(state.visibleIds), action)
    return ProductsState(idState.byId, visibleState.list)
}

fun products(state: ProductState, action: Action): ProductState {
    return when (action) {
        is AddToCart -> ProductState(state.product.copy(inventory = state.product.inventory.minus(1)))
        else -> state
    }
}

fun byId(state: ByIdState, action: Action): ByIdState {
    return when (action) {
        is ReceiveProducts -> {
            val reduce = mutableMapOf<Int, Product>()
            ByIdState(action.products.fold(reduce) { acc, product ->
                acc[product.id] = product
                acc
            })
        }
        is AddToCart -> {
            val productId = action.productId
            val newState = state.byId.toMutableMap()
            newState[productId] = products(ProductState(state.byId[productId]!!), action).product
            ByIdState(newState)
        }
        else -> state
    }
}

fun visibleIds(state: ListState, action: Action): ListState {
    return when (action) {
        is ReceiveProducts -> ListState(action.products.map { it.id })
        else -> state
    }
}

fun getProduct(state: ProductsState, id: Int) = state.byId[id]!!

fun getVisibleProducts(state: ProductsState) = state.visibleIds.map {
    getProduct(state, it)
}