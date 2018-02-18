package examples.shoppingCart.reducers

import constants.ActionTypes
import examples.shoppingCart.actions.ActionWithProducts
import examples.shoppingCart.api.Product
import redux.Action
import redux.Provider.store
import redux.State

//data class ProductsState(val inventory: Int = 0) : State
class EmptyAction(override val type: String) : Action

fun productsReducer() {
//    store.addReducer(::byId, "byId", ByIdState())
//    store.addReducer(::visibleIds, "visibleIds", ListState())
    store.addReducer(::combineProductsReducer, "products", ProductsState())
}

fun combineProductsReducer(state: ProductsState, action: Action): ProductsState{
    if (action is ActionWithProducts) {
        val idState = byId(ByIdState(), action)
        val visibleState = visibleIds(ListState(state.visibleIds), action)
        return ProductsState(idState.byId, visibleState.list)
    }
    return state
}
//fun products(state: ProductsState, action: EmptyAction): ProductsState {
//    return when (action.type) {
//        ActionTypes.ADD_TO_CART -> state.copy(inventory = state.inventory.minus(1))
//        else -> state
//    }
//}

data class ByIdState(val byId: Map<Int, Product> = mapOf()) : State

fun byId(state: ByIdState, action: ActionWithProducts): ByIdState {
    return when (action.type) {
        ActionTypes.RECEIVE_PRODUCTS -> {
            val reduce = mutableMapOf<Int, Product>()
            ByIdState(action.products.fold(reduce) { acc, product ->
                acc[product.id] = product
                acc
            })
        }
        else -> state
    }
}

data class ListState(val list: List<Int> = listOf()) : State

fun visibleIds(state: ListState, action: ActionWithProducts): ListState {
    return when (action.type) {
        ActionTypes.RECEIVE_PRODUCTS -> state//ListState(action.products.map { it.id })
        else -> state
    }
}

data class ProductsState(val byId: Map<Int, Product> = mapOf(), val visibleIds: List<Int> = listOf()): State
fun getProduct(state: ProductsState, id: Int) = state.byId[id]!!

fun getVisibleProducts(state: ProductsState) = state.visibleIds.map {
    getProduct(state, it)
}