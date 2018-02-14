package examples.shoppingCart.components

import ui.Component
import ui.container

class Product() : Component() {
    var title: String = ""
    var price: Int = 0
    var quantity: Int = 0

    constructor(title: String, price: Int, quantity: Int) : this() {
        this.title = title
        this.price = price
        this.quantity = quantity
    }

    override fun render() = container {
        text {
            +"$title - $price x $quantity"
        }
    }
}