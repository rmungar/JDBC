package org.example

import org.example.DAO.ProductDAO
import org.example.ENTITY.Product

fun main() {

    val product = Product(1, "Smartphone", 999.99f, "The latest smartphone model", "Apple", "Electronics")

    val productId = ProductDAO().createProduct(product)

    println("Product ID: $productId")

}