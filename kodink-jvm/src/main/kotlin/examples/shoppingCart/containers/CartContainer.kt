package examples.shoppingCart.containers

import examples.shoppingCart.actions.checkout
import examples.shoppingCart.components.Cart
import redux.Provider.store
import ui.Component
import ui.component

class CartContainer : Component() {
    init {
        store.subscribe {
            mapStateToProps()
        }
    }

    var products = listOf<CartProduct>()
    var total: Double = 0.0
    override fun render() = component(Cart()) {
        products = this@CartContainer.products
        total = this@CartContainer.total
        onCheckoutClicked = { checkout(this@CartContainer.products) }
    }

    fun mapStateToProps() {
        //TODO products = getCartProducts(store.getState())
        //TODO total = getTotal(store.getState())
        update()
    }

//    fun update() {
//        Platform.runLater({
//            children.clear()
//            create()
//        })
//    }
}

data class CartProduct(var id: Int = 0, var title: String = "", var price: Double = 0.0, var quantity: Int = 0)