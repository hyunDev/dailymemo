package org.hyunpro.webapp.dailymemo.Account;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepository {

    private Map<String, Account> accounts = new HashMap<>();

    public Account save(Account account) {
        account.setId(account.getId());
        accounts.put(account.getId(), account);
        return account;
    }

    public Account findById(String username){
        return accounts.get(username);
    }
}
