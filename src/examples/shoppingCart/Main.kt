import examples.shoppingCart.containers.App
import ui.runApp

fun main(args: Array<String>) {

    //val middleware = arrayListOf(createThunkMiddleware())
//    middleware.add(::logger)

//    Provider.createStore(reducer, applyMiddleware(middleware))

    //    Provider.store.dispatch(getAllProducts())

    runApp(App::class)
}