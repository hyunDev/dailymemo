package org.hyunpro.webapp.dailymemo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String create(Account account, HttpServletRequest httpServletRequest) throws Exception{
        accountService.saveAccount(account, httpServletRequest);
        return "redirect:/login";
    }

    @RequestMapping(value="/member/findPw", method = RequestMethod.GET)
    public String findPw(Account account){
        return "/member/findPw";
    }

    @RequestMapping(value="/member/findPw", method = RequestMethod.POST)
    public String sendMail(Account account){
        EmailServiceImpl es=new EmailServiceImpl();
        es.setJavaMailSender(javaMailSender);

        String tempPw = "";

        for (int i = 0; i < 12; i++) {
            tempPw += (char) ((Math.random() * 26) + 97);
        }

        String title = "DailyMemo 임시 비밀번호 발송입니다.";
        String contents = "임시 비밀번호는" + tempPw + "입니다. 비밀번호를 변경해주세요";

        es.sendSimpleMessage(account.getEmail(), title , contents);
        return "/login";
    }

    @RequestMapping(value="/member/signUp", method = RequestMethod.GET)
    public String signUp(){
        return "/member/signUp";
    }

    @RequestMapping(value="/getAccountId", method = RequestMethod.GET)
    public String getAccountId(@AuthenticationPrincipal Account account) {
        return account.getId();
    }

    @ResponseBody
    @RequestMapping(value="/checkId", method = RequestMethod.GET)
    public Boolean checkId(String id) {
        UserDetails userDetails = accountService.loadUserByUsername(id);

        if(userDetails == null)
            return true;
        else
            return false;
    }
}
