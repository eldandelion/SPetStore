package com.spookydan.spetstore.dao

import com.spookydan.spetstore.model.Account

interface AccountDao {
    fun getAccountByUsernameAndPassword(account: Account): Account?

    fun getAccountByUsername(username: String): Account?

    fun insertAccount(account: Account)

    fun insertProfile(account: Account)

    fun insertSignon(account: Account)

    fun updateAccount(account: Account)

    fun updateProfile(account: Account)

    fun updateSignon(account: Account)
}