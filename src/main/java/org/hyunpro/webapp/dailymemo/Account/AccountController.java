package org.hyunpro.webapp.dailymemo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String create(Account account, HttpServletRequest httpServletRequest) throws Exception{
        accountService.saveAccount(account, httpServletRequest);
        return "redirect:/login";
    }

    @RequestMapping(value="/member/signUp", method = RequestMethod.GET)
    public String signUp(){
        return "/member/signUp";
    }


}
