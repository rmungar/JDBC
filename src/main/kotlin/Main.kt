package org.example

import org.example.DAO.ProductDAO
import org.example.DSF.DataSourceFactory
import org.example.ENTITY.Product
import org.example.OUTPUT.Console
import javax.sql.DataSource

fun main() {
    val console = Console()
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    val product = Product(1, "Smartphone", 999.99f, "The latest smartphone model", "Apple", "Electronics")

    val productId = ProductDAO(dataSource, console).createProduct(product)

    println("Product ID: $productId")

}