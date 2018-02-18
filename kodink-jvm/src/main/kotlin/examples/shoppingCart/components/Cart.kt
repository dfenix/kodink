package examples.shoppingCart.components

import examples.shoppingCart.api.Product
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
                    container { +products.map { ProductComp(it.title, it.price, it.inventory) } }
                } else {
                    text { +"Please add some products to cart." }
                }
                text { +"Total: $total" }
            }
}