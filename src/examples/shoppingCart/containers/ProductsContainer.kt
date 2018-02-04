package examples.shoppingCart.containers

import examples.shoppingCart.components.Product
import examples.shoppingCart.components.ProductItem
import ui.Component
import ui.component
import examples.shoppingCart.components.ProductsList

class ProductsContainer: Component(){
    var products = mutableListOf<Product>()
    override fun render() = component(ProductsList()){
        title = "Products"
        +products.map{
            component(ProductItem()){
                product = it
            }
        }
    }
}