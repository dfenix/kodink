package examples.shoppingCart.components

import ui.Component
import ui.container

class Cart : Component() {
    var products = mutableListOf<Product>()
    var hasProducts = products.size > 0
    var total: Int = 0
    override fun render() =
            container {
                text { +"Your Cart" }
                if (hasProducts) {
                    container { +products }
                } else {
                    text { +"Please add some products to cart." }
                }
                text { +"Total: $total" }
            }
}