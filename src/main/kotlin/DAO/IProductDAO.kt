package org.example.DAO

import org.example.ENTITY.Product
import java.util.*

interface IProductDAO {
    fun createProduct(product: Product): Product?
    fun getAllProducts(): List<Product>
    fun getProductById(id: Int): Product?
    fun updateProducts(product: Product): Product?
    fun deleteProduct(id: Int): Boolean
}