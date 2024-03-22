package com.spookydan.spetstore.dao

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

object DBUtil {

    private const val DRIVER = "com.mysql.cj.jdbc.Driver"
    private const val URL = "jdbc:mysql://127.0.0.1:3306/jpetstore"
    private const val USERNAME = "root"
    private const val PASSWORD = "krokozyabra"

    fun getConnection(): Connection? {
        var connection: Connection? = null
        try {
            Class.forName(DRIVER)
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return connection
    }

    fun closeConnection(connection: Connection?) {
        connection?.let {
            try {
                connection.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun closeStatement(statement: Statement?) {
        statement?.let {
            try {
                statement.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun closeResultSet(resultSet: ResultSet?) {
        resultSet?.let {
            try {
                resultSet.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun closePreparedStatement(preparedStatement: PreparedStatement?) {
        preparedStatement?.let {
            try {
                preparedStatement.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}