package examples.shoppingCart.api

data class Product(val id: Int, val title: String, val price: Double, val inventory: Int)

fun products(): List<Product> {
    return listOf(
            Product(1, "iPad 4 Mini", 500.01, 2),
            Product(2, "H&M T-Shirt White", 10.99, 10),
            Product(3, "Charli XCX - Sucker CD", 19.99, 5)
    )
}