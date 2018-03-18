package examples.shoppingCart.api

data class Product(var id: Int = 0, var title: String = "", var price: Double = 0.0, var inventory: Int = 0, val quantity: Int = 0)

fun products(): List<Product> {
    return listOf(
            Product(1, "iPad 4 Mini", 500.01, 2),
            Product(2, "H&M T-Shirt White", 10.99, 10),
            Product(3, "Charli XCX - Sucker CD", 19.99, 5)
    )
}