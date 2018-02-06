package examples.shoppingCart.containers

import examples.shoppingCart.components.Cart
import examples.shoppingCart.components.Product
import ui.Component
import ui.component

class CartContainer: Component(){
    var products = mutableListOf<Product>()
    var total: Int = 0
    override fun render() = component(Cart()){

    }
}