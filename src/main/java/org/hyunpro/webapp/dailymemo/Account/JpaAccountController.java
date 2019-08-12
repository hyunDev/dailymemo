package org.hyunpro.webapp.dailymemo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JpaAccountController {

    @Autowired
    JpaAccountService JpaAccontService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String create(JpaAccount account, HttpServletRequest httpServletRequest) throws Exception{
        JpaAccontService.saveAccount(account, httpServletRequest);
        return "redirect:/login";
    }

    @RequestMapping(value="/member/signUp", method = RequestMethod.GET)
    public String signUp(){
        return "/member/signUp";
    }


}
