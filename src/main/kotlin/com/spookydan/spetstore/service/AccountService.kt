package com.spookydan.spetstore.service

import com.spookydan.spetstore.mappers.AccountMapper
import com.spookydan.spetstore.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class AccountService(
    @Autowired
    @Qualifier("accountDao")
    private val accountMapper: AccountMapper
) {




    fun getAccount(username: String, password: String): Account? {
        val account = Account().apply {
            this.username = username
            this.password = password
        }
        return accountMapper.getAccountByUsernameAndPassword(account)
    }


    fun insertAccount(account: Account) {
        accountMapper.apply {
            insertAccount(account)
            insertProfile(account)
            insertSignon(account)
        }
    }

    fun updateAccount(account: Account) {
        accountMapper.apply {
            updateAccount(account)
            updateProfile(account)
            if (account.password.isNotEmpty()) {
                updateSignon(account)
            }
        }
    }

    fun getAccountByUsername(username: String): Account? {
        return accountMapper.getAccountByUsername(username)
    }

}