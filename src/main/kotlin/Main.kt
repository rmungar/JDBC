package org.example

import org.example.DAO.ProductDAO
import org.example.ENTITY.Product
import org.example.OUTPUT.Console

fun main() {
    val console = Console()

    val product = Product(2, "Smartphone", 999.99f, "The latest smartphone model", "Samsung", "Electronics")

    val productId = ProductDAO(console).createProduct(product)

    println("Product ID: $productId")

}