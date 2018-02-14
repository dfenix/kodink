package examples.shoppingCart.reducers

import constants.ActionTypes
import examples.shoppingCart.actions.ActionWithProducts
import redux.Action
import redux.Provider.store
import redux.State

data class ProductsState(val inventory: Int = 0) : State
class EmptyAction(override val type: String) : Action

fun productsReducer() {
    store.addReducer(::byId, ByIdState())
}

fun products(state: ProductsState, action: EmptyAction): ProductsState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> state.copy(inventory = state.inventory.minus(1))
        else -> state
    }
}

data class ByIdState(val id: String = "") : State

fun byId(state: ByIdState, action: ActionWithProducts): ByIdState {
    return when (action.type) {
        ActionTypes.RECEIVE_PRODUCTS -> state //action.products
        else -> state
    }
}