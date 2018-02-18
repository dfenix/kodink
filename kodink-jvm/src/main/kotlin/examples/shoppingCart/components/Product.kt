package examples.shoppingCart.components

import ui.Component
import ui.container

class ProductComp(var title: String = "", var price: Double = 0.0, var quantity: Int = 0) : Component() {
    override fun render() = container {
        text {
            +"$title - $$price x $quantity"
        }
    }
}