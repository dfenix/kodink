package examples.shoppingCart.reducers

import constants.ActionTypes
import redux.Action
import redux.Provider.store
import redux.State

fun cartReducer(){
    store.addReducer(::cart, InitialState())
}

data class InitialState(
        val addedIds: List<String> = listOf(),
        val quatityById: Map<String, Int> = mapOf()
) : State

fun cart(state: InitialState, action: CartAction): InitialState {
    return when (action.type) {
        ActionTypes.CHECKOUT_REQUEST -> InitialState()
        ActionTypes.CHECKOUT_FAILURE -> action.cart//TODO check this
        else -> {
            val action2 = ProductIDAction("", "")
            InitialState(
                    addedIds = addedIds(InitialState(state.addedIds), action2/*action*/).addedIds,
                    quatityById = quantityById(InitialState(quatityById = state.quatityById), action2 /*action*/).quatityById
            )
        }
    }
}

data class ProductIDAction(override val type: String, val productId: String) : Action

fun addedIds(state: InitialState, action: ProductIDAction): InitialState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> {
            if (state.addedIds.contains(action.productId)) {
                state
            } else {
                InitialState(addedIds = state.addedIds.toList() + action.productId)
            }

        }
        else -> state
    }
}

fun quantityById(state: InitialState, action: ProductIDAction): InitialState {
    return when (action.type) {
        ActionTypes.ADD_TO_CART -> {
            val value = state.quatityById[action.productId]?.plus(1) ?: 0
            state.copy(quatityById = state.quatityById.toMap() + Pair(action.productId, value))
        }
        else -> state
    }
}

data class CartAction(override val type: String, val cart: InitialState) : Action

