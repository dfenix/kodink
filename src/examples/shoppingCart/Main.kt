import examples.shoppingCart.actions.getAllproducts
import examples.shoppingCart.containers.App
import examples.shoppingCart.reducers.combineReducers
import ui.runApp

fun main(args: Array<String>) {
    combineReducers()
    getAllproducts()
    runApp(App::class)
}