package org.example.DAO

import org.example.DSF.DataBase
import org.example.ENTITY.Product
import org.example.OUTPUT.IOutput
import java.sql.SQLException

class ProductDAO(private val console: IOutput ): IProductDAO {

    private val connection = DataBase.getConnection()

    override fun createProduct(product: Product): Product? {
        val sql = "INSERT INTO PRODUCTS VALUES (?, ?, ?, ?, ?, ?)"
        return try {
            connection.use { connection ->
                connection?.prepareStatement(sql).use { statement ->
                    statement?.setString(1, product.id.toString())
                    statement?.setString(2, product.name)
                    statement?.setString(3, product.price.toString())
                    statement?.setString(4, product.description)
                    statement?.setString(5, product.brand)
                    statement?.setString(6, product.category)
                    val rs = statement?.executeUpdate()
                    if (rs == 1){
                        connection?.close()
                        product
                    }
                    else{
                        connection?.close()
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

    override fun getAllProducts(): List<Product>? {
        val sql = "SELECT * FROM tuser"
        return try {
            connection.use { connection ->
                connection?.prepareStatement(sql).use { stmt ->
                    val rs = stmt?.executeQuery()
                    val products = mutableListOf<Product>()
                    while (rs!!.next()) {
                        products.add(
                            Product(
                                id = (rs.getString("id").toInt()),
                                name = rs.getString("name"),
                                price = rs.getString("price").toFloat(),
                                description = rs.getString("description"),
                                brand = rs.getString("brand"),
                                category = rs.getString("category")
                            )
                        )
                    }
                    connection?.close()
                    products
                }
            }
        }catch (e:SQLException){
            console.showMessage(e.message!!, true)
            connection?.close()
            null

        }catch (e:Exception){
            console.showMessage(e.message!!, true)
            connection?.close()
            null
        }
    }

    override fun getProductById(id: Int): Product? {
        val sql = "SELECT * FROM PRODUCT WHERE id = (?)"
        return try {
            connection.use { connection ->
                connection?.prepareStatement(sql).use { statement ->
                    statement?.setString(1, id.toString())
                    val rs = statement?.executeQuery()
                    if (rs!!.next()) {
                        Product(
                            id = rs.getString("id").toInt(),
                            name = rs.getString("name"),
                            price = rs.getString("price").toFloat(),
                            description = rs.getString("description"),
                            brand = rs.getString("brand"),
                            category = rs.getString("category")
                        )
                    } else {
                        console.showMessage("-- ERROR AL OBTENER EL PRODUCTO --", true)
                        null
                    }
                }
            }
        }catch (e:SQLException){
            console.showMessage(e.message!!, true)
            connection?.close()
            null

        } catch (e:Exception){
            console.showMessage(e.message!!, true)
            connection?.close()
            null
        }
    }

    override fun updateProducts(product: Product): Product? {
        val sql = "UPDATE PRODUCTS SET id = ?, name = ?, price = ?, description = ?, brand = ?, category = ?"
        return try {
            connection.use { connection ->
                connection?.prepareStatement(sql).use { statement ->
                    statement?.setString(1, product.id.toString())
                    statement?.setString(2, product.name)
                    statement?.setString(3, product.price.toString())
                    statement?.setString(4, product.description)
                    statement?.setString(5, product.brand)
                    statement?.setString(6, product.category)
                    statement?.executeUpdate()
                    connection?.close()
                    product
                }
            }
        } catch (e:SQLException){
            console.showMessage(e.message.toString(), true)
            connection?.close()
            null

        } catch (e:Exception){
            console.showMessage(e.message!!, true)
            connection?.close()
            null
        }
    }

    override fun deleteProduct(id: Int): Boolean {
        val sql = "DELETE FROM PRODUCTS WHERE id = (?)"
        return try {
            connection.use {connection ->
                connection?.prepareStatement(sql).use { statement ->
                    statement?.setString(1, id.toString())
                    statement?.executeUpdate()
                    connection?.close()
                    true
                }
            }
        } catch (e:SQLException){
            console.showMessage(e.message!!, true)
            connection?.close()
            false

        }catch (e:Exception){
            console.showMessage(e.message!!, true)
            connection?.close()
            false
        }
    }
}