package examples.shoppingCart.components

import examples.shoppingCart.api.Product
import javafx.scene.Node
import ui.Component
import ui.container

class ProductItem : Component() {
    var product: Product = Product()
    override fun render(): Node = container {
        component(ProductComp()) {
            title = product.title
            price = product.price
            quantity = product.inventory
        }
        button {
            setOnAction { function("onAddToCartClicked")() }
            +"Add to Cart"
        }
    }
}