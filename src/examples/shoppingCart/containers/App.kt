package examples.shoppingCart.containers

import examples.shoppingCart.components.Product
import javafx.scene.Parent
import ui.RunApplication
import ui.component
import ui.container

class App : RunApplication() {
    override fun render(): Parent{
        //remove this block
//        class Product(val title: String, val price: Int, val inventory: Int)

        val products = listOf(
                Product("jugo naranja", 100, 5),
                Product("manzana", 2, 20),
                Product("chile", 3, 14)
        )

//        val onAddToCartClicked = { println("click!")}
        //remove this block

        return container {
            text { +"Shopping Cart Example" }
            component(ProductsContainer()){
                this.products = products.toMutableList()
            }
            /*container {
                text { +"Products" }
                container {
                    for (product in products) {
                        container {
                            style = "margin-bottom: 20px;"
                            container {
                                +"${product.title} - ${product.price} x ${product.inventory}"
                            }
                            button {
                                onClick = onAddToCartClicked
                                _disabled = product.inventory > 0
                                if (product.inventory > 0) +"Add to cart" else +"Sold Out"
                            }
                        }
                    }
                }
            }*/
        }
    }
}