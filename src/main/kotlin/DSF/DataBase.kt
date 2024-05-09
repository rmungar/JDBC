package org.example.DSF

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DataBase {

    const val DB_URL = "jdbc:h2:file:C:\\Users\\usuario tarde\\ReposGit\\JDBC\\default"
    const val USER = "user"
    const val PASS = "user"

    // Función para establecer una conexión a la base de datos
    fun getConnection(): Connection? {
        var conn: Connection? = null
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS)
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
        return conn
    }

    // Función para cerrar una conexión a la base de datos
    fun closeConnection(conn: Connection?) {
        try {
            conn?.close()
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
}