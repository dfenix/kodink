package examples.shoppingCart.containers

import javafx.scene.Parent
import ui.Application
import ui.container

class App : Application() {
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