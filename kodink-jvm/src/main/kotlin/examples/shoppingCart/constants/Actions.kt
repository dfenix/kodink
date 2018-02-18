package examples.shoppingCart.constants

import examples.shoppingCart.api.Product
import redux.Action

class ReceiveProducts(val products: List<Product>) : Action

data class AddToCart(val productId: Int) : Action

class CheckoutRequest : Action

class CheckoutFailure(val cart: CartState) : Action

class CheckoutSuccess(val cart: CartState) : Action
