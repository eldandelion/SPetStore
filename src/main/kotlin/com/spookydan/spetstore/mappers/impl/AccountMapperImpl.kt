package com.spookydan.spetstore.mappers.impl

import com.spookydan.spetstore.mappers.AccountMapper
import com.spookydan.spetstore.mappers.DBUtil
import com.spookydan.spetstore.model.Account
import com.spookydan.spetstore.model.Enums
import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.logging.Logger

@Repository("accountDao")
class AccountMapperImpl(
    @Autowired
    private val sqlSession: SqlSession
) : AccountMapper {


    companion object {
        private const val GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT " +
                "SIGNON.USERNAME," +
                "ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS," +
                "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
                "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE," +
                "PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
                "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption," +
                "BANNERDATA.BANNERNAME " +
                "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
                "WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
                "AND SIGNON.USERNAME = ACCOUNT.USERID " +
                "AND PROFILE.USERID = ACCOUNT.USERID " +
                "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY"

        private const val GET_ACCOUNT_BY_USERNAME = """
        SELECT
            SIGNON.USERNAME,
            ACCOUNT.EMAIL,
            ACCOUNT.FIRSTNAME,
            ACCOUNT.LASTNAME,
            ACCOUNT.STATUS,
            ACCOUNT.ADDR1 AS address1,
            ACCOUNT.ADDR2 AS address2,
            ACCOUNT.CITY,
            ACCOUNT.STATE,
            ACCOUNT.ZIP,
            ACCOUNT.COUNTRY,
            ACCOUNT.PHONE,
            PROFILE.LANGPREF AS languagePreference,
            PROFILE.FAVCATEGORY AS favouriteCategoryId,
            PROFILE.MYLISTOPT AS listOption,
            PROFILE.BANNEROPT AS bannerOption,
            BANNERDATA.BANNERNAME
        FROM
            ACCOUNT, PROFILE, SIGNON, BANNERDATA
        WHERE
            ACCOUNT.USERID = ? AND
            SIGNON.USERNAME = ACCOUNT.USERID AND
            PROFILE.USERID = ACCOUNT.USERID AND
            PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY
    """

        private const val INSERT_ACCOUNT = """
        INSERT INTO ACCOUNT(
            USERID,
            EMAIL,
            FIRSTNAME,
            LASTNAME,
            STATUS,
            ADDR1,
            ADDR2,
            CITY,
            STATE,
            ZIP,
            COUNTRY,
            PHONE
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """

        private const val INSERT_PROFILE = """
        INSERT INTO PROFILE(
            LANGPREF,
            FAVCATEGORY,
            MYLISTOPT,
            BANNEROPT,
            USERID
        )
        VALUES (?, ?, ?, ?, ?)
    """

        private const val INSERT_SIGNON = """
        INSERT INTO SIGNON(
            USERNAME,
            PASSWORD
        )
        VALUES (?, ?)
    """

        private const val UPDATE_ACCOUNT = """
        UPDATE ACCOUNT
        SET
            EMAIL = ?,
            FIRSTNAME = ?,
            LASTNAME = ?,
            STATUS = ?,
            ADDR1 = ?,
            ADDR2 = ?,
            CITY = ?,
            STATE = ?,
            ZIP = ?,
            COUNTRY = ?,
            PHONE = ?
        WHERE
            USERID = ?
    """

        private const val UPDATE_PROFILE = """
        UPDATE PROFILE
        SET
            LANGPREF = ?,
            FAVCATEGORY = ?,
            MYLISTOPT = ?,
            BANNEROPT = ?
        WHERE
            USERID = ?
    """

        private const val UPDATE_SIGNON = """
        UPDATE SIGNON
        SET
            PASSWORD = ?
        WHERE
            USERNAME = ?
    """

    }

    private val mapper = sqlSession.getMapper(AccountMapper::class.java)

    override fun getAccountByUsernameAndPassword(account: Account): Account? {
        var accountResult: Account? = null
        try {
            accountResult = mapper.getAccountByUsernameAndPassword(account)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return accountResult
    }

    private fun resultSetToAccount(resultSet: ResultSet): Account {
        return Account().apply {
            username = resultSet.getString("username")
            email = resultSet.getString("email")
            firstName = resultSet.getString("firstName")
            lastName = resultSet.getString("lastName")
            status = resultSet.getString("status")
            address1 = resultSet.getString("address1")
            address2 = resultSet.getString("address2")
            city = resultSet.getString("city")
            state = resultSet.getString("state")
            zip = resultSet.getString("zip")
            country = resultSet.getString("country")
            phone = resultSet.getString("phone")
            favouriteCategoryId = resultSet.getString("favouriteCategoryId")
            languagePreference = resultSet.getString("languagePreference")
            listOption = resultSet.getInt("listOption") == Enums.LIST_OPTION_ON
            bannerOption = resultSet.getInt("bannerOption") == Enums.BANNER_OPTION_ON
            bannerName = resultSet.getString("bannerName")
        }
    }

    private val logger = Logger.getLogger("AccountDao")

    override fun getAccountByUsername(username: String): Account? {
        var accountResult: Account? = null
        try {
            accountResult = mapper.getAccountByUsername(username)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return accountResult
    }

    override fun insertAccount(account: Account) {
        try {
            mapper.insertAccount(account)
            mapper.insertProfile(account)
            mapper.insertSignon(account)
            sqlSession.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun updateAccount(account: Account) {
        try {
            mapper.updateAccount(account)
            mapper.updateProfile(account)
            mapper.updateSignon(account)
            sqlSession.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun updateProfile(account: Account) {
        try {
            mapper.updateProfile(account)
            sqlSession.commit()
            sqlSession.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun updateSignon(account: Account) {
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(UPDATE_SIGNON)
            preparedStatement?.apply {
                setString(1, account.password)
                setString(2, account.username)
                executeUpdate()
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    override fun insertProfile(account: Account) {
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(INSERT_PROFILE)
            preparedStatement?.apply {
                setString(1, account.languagePreference)
                setString(2, account.favouriteCategoryId)
                setInt(3, if (account.listOption) Enums.LIST_OPTION_ON else Enums.LIST_OPTION_OFF)
                setInt(4, if (account.bannerOption) Enums.BANNER_OPTION_ON else Enums.BANNER_OPTION_OFF)
                setString(5, account.username)
                executeUpdate()
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun insertSignon(account: Account) {
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(INSERT_SIGNON)
            preparedStatement?.apply {
                setString(1, account.username)
                setString(2, account.password)
                executeUpdate()
                DBUtil.closePreparedStatement(this)
            }
            DBUtil.closeConnection(connection)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}