package org.hyunpro.webapp.dailymemo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accontService;

    @GetMapping("/create")
    public Account create(){
        Account account = new Account();
        account.setId("hyunseo");
        account.setEmail("var.hyunseo@gmail.com");
        account.setPassword("password");

        return accontService.save(account);
    }
}
