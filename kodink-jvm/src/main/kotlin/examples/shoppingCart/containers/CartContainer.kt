package examples.shoppingCart.containers

import examples.shoppingCart.actions.checkout
import examples.shoppingCart.components.Cart
import examples.shoppingCart.reducers.getCartProducts
import examples.shoppingCart.reducers.getTotal
import javafx.application.Platform
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
        products = getCartProducts(store.getState())
        total = getTotal(store.getState())
        update()
    }

    fun update() {
        Platform.runLater({
            children.clear()
            create()
        })
    }
}

data class CartProduct(var id: Int = 0, var title: String = "", var price: Double = 0.0, var quantity: Int = 0)