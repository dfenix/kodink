package examples.shoppingCart.containers

import examples.shoppingCart.api.Product
import examples.shoppingCart.components.Cart
import examples.shoppingCart.reducers.getCartProducts
import examples.shoppingCart.reducers.getTotal
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
        this.products = products
        this.total = total
    }

    fun mapStateToProps(){
        products = getCartProducts(store.getState())
        total = getTotal(store.getState())
    }
}

data class CartProduct(var id: Int = 0, var title: String = "", var price: Double = 0.0, var quantity: Int = 0)