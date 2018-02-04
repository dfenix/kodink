package examples.shoppingCart.components

import ui.Component
import ui.container

class ProductsList: Component(){

    var title: String = ""

    override fun render() = container {
        text {
            +title
        }
    }
}