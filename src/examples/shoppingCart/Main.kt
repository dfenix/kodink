import ui.app
import ui.runApp

fun main(args: Array<String>) {

    val middleware = arrayListOf(createThunkMiddleware())
//    middleware.add(::logger)

//    Provider.createStore(reducer, applyMiddleware(middleware))

    //    Provider.store.dispatch(getAllProducts())
    class Product(val title: String, val price: Int, val inventory: Int)

    val products = listOf(
            Product("jugo naranja", 100, 5),
            Product("manzana", 2, 20),
            Product("chile", 3, 14)
    )

    val onAddToCartClicked = { println("click!")}
    runApp(
            app {
                container {
                    text { +"Shopping Cart Example" }
                    container {
                        text { +"Products" }
                        container {
                            for (product in products) {
                                container {
                                    style = "margin-bottom: 20px;"
                                    container {
                                        +"${product.title} - ${product.price} x ${product.inventory}"
                                    }
                                    button {
                                        onClick = onAddToCartClicked
                                        _disabled = product.inventory > 0
                                        if (product.inventory > 0) +"Add to cart" else +"Sold Out"
                                    }
                                }
                            }
                        }
                    }
                }
            }
    )
}
/*
div{
    h2{ "Shopping Cart Example" }
    div{
        h3{"Products"}
        div{
            for(product in products){
                div{
                    style = "margin-bottom: 20px;"
                    div{
                        "${product.title} - ${product.price} x ${product.inventory}"
                    }
                    button{
                        onClick = onAddToCartClicked
                        disabled = product.inventory > 0
                        if(product.inventory > 0) "Add to cart" else "Sold Out"
                    }
                }
            }
        }
    }
}
*/