package examples.shoppingCart

import examples.shoppingCart.actions.getAllproducts
import examples.shoppingCart.constants.AppState
import examples.shoppingCart.containers.App
import examples.shoppingCart.reducers.reducer
import middleware.Logger
import redux.Provider
import redux.Provider.store
import ui.run

fun main(args: Array<String>) {
    Provider.createStore(reducer(), AppState())
    store.applyMiddleware(Logger())
    getAllproducts()
    run(App::class)
}