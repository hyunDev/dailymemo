package org.hyunpro.webapp.dailymemo.Account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account, Integer> {


    @Query("SELECT account FROM Account account WHERE account.id = :id")
    Account findById(@Param("id") String id);

}
