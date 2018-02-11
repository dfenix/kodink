package examples.shoppingCart.containers

import examples.shoppingCart.actions.addToCart
import examples.shoppingCart.components.Product
import examples.shoppingCart.components.ProductItem
import examples.shoppingCart.components.ProductsList
import ui.Component
import ui.component

class ProductsContainer : Component() {
    var products = mutableListOf<Product>()
    override fun render() = component(ProductsList()) {
        title = "Products"
        +products.map {
            component(ProductItem()) {
                product = it
                subscribe("onAddToCartClicked", { addToCart(it.title) })
            }
        }
    }
}
