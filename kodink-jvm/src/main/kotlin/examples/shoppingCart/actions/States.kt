package examples.shoppingCart.actions

import examples.shoppingCart.api.Product
import redux.State

data class ByIdState(val byId: Map<Int, Product> = mapOf()) : State

data class ListState(val list: List<Int> = listOf()) : State

data class ProductsState(val byId: Map<Int, Product> = mapOf(), val visibleIds: List<Int> = listOf()) : State

data class ProductState(val product: Product = Product()) : State

data class CartState(
        val addedIds: List<Int> = listOf(),
        val quatityById: Map<Int, Int> = mapOf()
) : State
