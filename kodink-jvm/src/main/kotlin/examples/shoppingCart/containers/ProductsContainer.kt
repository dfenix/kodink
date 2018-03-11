package examples.shoppingCart.containers

import examples.shoppingCart.api.Product
import examples.shoppingCart.components.ProductItem
import examples.shoppingCart.components.ProductsList
import redux.Provider.store
import ui.Component
import ui.component


class ProductsContainer : Component() {
    var products = mutableListOf<Product>()

    init {
        store.subscribe {
            mapStateToProps()
        }
    }

    override fun render() = component(ProductsList()) {
        title = "Products"
        +products.map {
            component(ProductItem()) {
                product = it
//                subscribe("onAddToCartClicked", { addToCart(it.id) })
            }
        }
    }

    fun mapStateToProps() {
// TODO       products = getVisibleProducts(store.getState()).toMutableList()
        update()
    }

//    fun update() {
//        Platform.runLater({
//            children.clear()
//            create()
//        })
//    }
}
