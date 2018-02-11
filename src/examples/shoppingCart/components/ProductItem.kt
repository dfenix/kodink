package examples.shoppingCart.components

import javafx.scene.Node
import ui.Component
import ui.container

class ProductItem : Component() {
    var product: Product = Product()
    override fun render(): Node = container {
        component(Product()) {
            title = product.title
            price = product.price
            quantity = product.quantity
        }
        button {
            setOnAction { function("onAddToCartClicked")() }
            +"Add to Cart"
        }
    }
}