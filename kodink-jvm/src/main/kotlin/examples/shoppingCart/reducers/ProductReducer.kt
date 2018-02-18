package examples.shoppingCart.reducers

import constants.ActionTypes
import examples.shoppingCart.actions.*
import examples.shoppingCart.api.Product
import redux.Action
import redux.Provider.store

//data class ProductsState(val inventory: Int = 0) : State

fun productsReducer() {
//    store.addReducer(::byId, "byId", ByIdState())
//    store.addReducer(::visibleIds, "visibleIds", ListState())
    store.addReducer(::combineProductsReducer, "products", ProductsState())
}

fun combineProductsReducer(state: ProductsState, action: Action): ProductsState {
//    if (action is ActionWithProducts) {
    val idState = byId(ByIdState(state.byId), action)
    val visibleState = visibleIds(ListState(state.visibleIds), action)
    return ProductsState(idState.byId, visibleState.list)
//    }
//    return state
}

fun products(state: ProductState, action: Action): ProductState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> ProductState(state.product.copy(inventory = state.product.inventory.minus(1)))
        else -> state
    }
}

fun byId(state: ByIdState, action: Action): ByIdState {
    if (action.type == ActionTypes.RECEIVE_PRODUCTS && action is ActionWithProducts) {
        val reduce = mutableMapOf<Int, Product>()
        return ByIdState(action.products.fold(reduce) { acc, product ->
            acc[product.id] = product
            acc
        })
    } else if (action is CartAction) {
        val productId = action.productId
        val newState = state.byId.toMutableMap()
        newState[productId] = products(ProductState(state.byId[productId]!!), action).product
        return ByIdState(newState)
    }
    return state
}

fun visibleIds(state: ListState, action: Action): ListState {
    return if (action.type == ActionTypes.RECEIVE_PRODUCTS && action is ActionWithProducts) {
        ListState(action.products.map { it.id })
    } else {
        state
    }
}

fun getProduct(state: ProductsState, id: Int) = state.byId[id]!!

fun getVisibleProducts(state: ProductsState) = state.visibleIds.map {
    getProduct(state, it)
}