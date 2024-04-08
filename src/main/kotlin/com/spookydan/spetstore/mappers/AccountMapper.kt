package com.spookydan.spetstore.mappers

import com.spookydan.spetstore.model.Account
import org.apache.ibatis.annotations.Mapper

@Mapper
interface AccountMapper {
    fun getAccountByUsernameAndPassword(account: Account): Account?

    fun getAccountByUsername(username: String): Account?

    fun insertAccount(account: Account)

    fun insertProfile(account: Account)

    fun insertSignon(account: Account)

    fun updateAccount(account: Account)

    fun updateProfile(account: Account)

    fun updateSignon(account: Account)
}