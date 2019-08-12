package org.hyunpro.webapp.dailymemo.Account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaAccountRepository extends CrudRepository<JpaAccount, Integer> {


    @Query("SELECT account FROM JpaAccount account WHERE account.id = :id")
    JpaAccount findById(@Param("id") String id);

}
