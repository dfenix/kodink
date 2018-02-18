package examples.shoppingCart.components

import examples.shoppingCart.containers.CartProduct
import ui.Component
import ui.container

class Cart : Component() {
    var products = listOf<CartProduct>()
    var hasProducts: Boolean = false
        get() = products.isNotEmpty()
    var total: Double = 0.0
    override fun render() =
            container {
                text { +"Your Cart" }
                if (hasProducts) {
                    container { +products.map { ProductComp(it.title, it.price, it.quantity) } }
                } else {
                    text { +"Please add some products to cart." }
                }
                text { +"Total: $total" }
            }
}