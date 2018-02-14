package examples.shoppingCart.containers

import javafx.scene.Parent
import ui.RunApplication
import ui.container

class App : RunApplication() {
    override fun render(): Parent {
        return container {
            text { +"Shopping Cart Example" }
            component(ProductsContainer()) {
                this.products = products.toMutableList()
            }
            component(CartContainer()) {
                this.products = products.toMutableList()
            }
        }
    }
}