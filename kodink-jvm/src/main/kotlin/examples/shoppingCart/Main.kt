package examples.shoppingCart

import examples.shoppingCart.actions.getAllproducts
import examples.shoppingCart.containers.App
import examples.shoppingCart.reducers.combineReducers
import ui.run

fun main(args: Array<String>) {
    combineReducers()
    getAllproducts()
    getAllproducts()
    //TODO store.applyMiddleware(::logger)
    run(App::class)
}