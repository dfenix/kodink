import examples.shoppingCart.actions.getAllproducts
import examples.shoppingCart.containers.App
import examples.shoppingCart.reducers.combineReducers
import redux.Provider.store
import ui.runApp

fun main(args: Array<String>) {
    combineReducers()
    getAllproducts()
    getAllproducts()
    store.applyMiddleware(::logger)
    runApp(App::class)
}