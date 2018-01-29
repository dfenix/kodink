import examples.shoppingCart.containers.App
import ui.runApp

fun main(args: Array<String>) {

    //val middleware = arrayListOf(createThunkMiddleware())
//    middleware.add(::logger)

//    Provider.createStore(reducer, applyMiddleware(middleware))

    //    Provider.store.dispatch(getAllProducts())

    runApp(App::class)
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