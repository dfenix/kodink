package examples.shoppingCart.containers

import examples.shoppingCart.api.Product
import examples.shoppingCart.components.Cart
import ui.Component
import ui.component

class CartContainer : Component() {
    var products = mutableListOf<Product>()
    var total: Int = 0
    override fun render() = component(Cart()) {

    }
}