package org.example.DAO

import org.example.DSF.DataSourceFactory
import org.example.ENTITY.Product
import org.example.OUTPUT.Console
import org.example.OUTPUT.IOutput
import java.sql.Connection
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

class ProductDAO(private val dataSource :  DataSource, private val console: IOutput ): IProductDAO {



    override fun createProduct(product: Product): Product? {
        val sql = "INSERT INTO PRODUCTS VALUES (?, ?, ?, ?, ?, ?)"
        return try {
            dataSource.connection.use { connection ->
                connection.prepareStatement(sql).use { statement ->
                    statement.setString(1, product.id.toString())
                    statement.setString(2, product.name)
                    statement.setString(3, product.price.toString())
                    statement.setString(4, product.description)
                    statement.setString(5, product.brand)
                    statement.setString(6, product.category)
                    val rs = statement.executeUpdate()
                    if (rs == 1){
                        product
                    }
                    else{
                        null
                    }
                }
            }
        }
        catch (e:SQLException){
            console.showMessage(e.message!!, true)
            return null
        }
    }

    override fun getAllProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun getProductById(id: Int): Product? {
        TODO("Not yet implemented")
    }

    override fun updateProducts(product: Product): Product? {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}