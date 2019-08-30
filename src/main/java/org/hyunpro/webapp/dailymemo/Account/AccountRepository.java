package org.hyunpro.webapp.dailymemo.Account;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AccountRepository extends CrudRepository<Account, Integer> {


    @Query("SELECT account FROM Account account WHERE account.id = :id")
    Account findById(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("UPDATE Account SET password = :password where id = :id")
    void updatePw(@Param("id") String id, @Param("password") String password);

}
