package com.spookydan.spetstore.dao.impl

import com.spookydan.spetstore.dao.DBUtil
import com.spookydan.spetstore.dao.SequenceDao
import com.spookydan.spetstore.model.Sequence
import org.springframework.stereotype.Repository
import java.sql.Connection

@Repository("SequenceDao")
class SequenceDaoImpl : SequenceDao {

    companion object {

        private const val UPDATE_SEQUENCE = "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?"
        private const val GET_SEQUENCE = "SELECT NAME, NEXTID FROM SEQUENCE WHERE NAME = ?"

    }

    override fun getSequence(sequence: Sequence): Sequence {
        try {
            val connection: Connection? = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(GET_SEQUENCE)
            preparedStatement?.apply {
                setString(1, sequence.getName())
                val resultSet = executeQuery()
                if (resultSet.next()) {
                    sequence.setName(resultSet.getString(1))
                    sequence.setNextId(resultSet.getInt(2))
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sequence
    }

    override fun updateSequence(sequence: Sequence) {
        try {
            val connection: Connection? = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(UPDATE_SEQUENCE)
            preparedStatement?.apply {
                setInt(1, sequence.getNextId())
                setString(2, sequence.getName())
                executeUpdate()
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}