package examples.shoppingCart.constants

import examples.shoppingCart.api.Product
import redux.State

typealias addedIds = List<Int>
typealias quantityById = Map<Int, Int>

class CartState(val addedIds: addedIds = listOf(), val quantityById: quantityById = mapOf()) : State

typealias byId = Map<Int, Product>
typealias visibleIds = List<Int>

class ProductsState(val byId: byId = mapOf(), val visibleIds: visibleIds = listOf()) : State

class AppState(val cart: CartState = CartState(), val products: ProductsState = ProductsState()) : State

/*fun initialState(): States {
    val cart = States()
    cart["addedIds"] = listOf<Int>()
    cart["quantityById"] = mapOf<Int, Int>()

    val products = States()
    products["byId"] = mapOf<Int, Product>()
    products["visibleIds"] = listOf<Int>()

    val app = States()
    app["cart"] = cart
    app["products"] = products

    return app
}*/