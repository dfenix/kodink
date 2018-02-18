package examples.shoppingCart.actions

import examples.shoppingCart.api.Product
import redux.Action

class ActionWithProducts(override val type: String, val products: List<Product>) : Action

data class CartAction(override val type: String, val productId: Int) : Action

class EmptyAction(override val type: String) : Action

class ActionWithCart(override val type: String, val cart: CartState) : Action