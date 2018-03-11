package examples.shoppingCart.constants

import examples.shoppingCart.api.Product

//data class ByIdState(val byId: Map<Int, Product> = mapOf()) : State

//data class ListState(val list: List<Int> = listOf()) : State

//data class ProductsState(val byId: Map<Int, Product> = mapOf(), val visibleIds: List<Int> = listOf()) : State

//data class ProductState(val product: Product = Product()) : State

//data class CartState(
//        val addedIds: List<Int> = listOf(),
//        val quatityById: Map<Int, Int> = mapOf()
//) : State

//data class AppState(var cart: CartState = CartState(), var products: ProductsState = ProductsState()) : State {
//    val addedIds
//        get() = cart.addedIds
//    val quatityById
//        get() = cart.quatityById
//    val byId
//        get() = products.byId
//    val visibleIds
//        get() = products.visibleIds
//
//    constructor(
//            addedIds: List<Int> = listOf(),
//            quatityById: Map<Int, Int> = mapOf(),
//            byId: Map<Int, Product> = mapOf(),
//            visibleIds: List<Int> = listOf()
//    ) : this() {
//        cart = CartState(addedIds, quatityById)
//        products = ProductsState(byId, visibleIds)
//    }
//}

data class AppState(
        val addedIds: List<Int> = listOf(),
        val quatityById: Map<Int, Int> = mapOf(),
        val byId: Map<Int, Product> = mapOf(),
        val visibleIds: List<Int> = listOf()
) //TODO: State