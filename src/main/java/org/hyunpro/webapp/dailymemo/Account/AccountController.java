package org.hyunpro.webapp.dailymemo.Account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
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
    public String create(Account account) throws Exception{
        accountService.saveAccount(account);
        return "redirect:/login";
    }

    @RequestMapping(value="/member/findPw", method = RequestMethod.GET)
    public String findPw(){
        return "/member/findPw";
    }

    @ResponseBody
    @RequestMapping(value="/member/findPw", method = RequestMethod.POST)
    public boolean sendMail(Account account) throws Exception{
        EmailServiceImpl es=new EmailServiceImpl();
        es.setJavaMailSender(javaMailSender);

        // 입력한 아이디로 이메일 가져오기
        String email = accountService.getEmail(account.getId());

        //입력한 이메일과 아이디의 이메일 같을 때
        if(email.equals(account.getEmail())) {
            String tempPw = accountService.getTempPw();
            String title = "DailyMemo 임시 비밀번호 발송입니다.";
            String contents = "임시 비밀번호는" + tempPw + "입니다. 비밀번호를 변경해주세요";
            account.setPassword(tempPw);
            // id 비밀번호 임시비밀번호로 세팅
            accountService.saveAccount(account);
            return es.sendSimpleMessage(email, title, contents); // 메일 전송 성공시 true 반환
        }
        else
            return false;
    }

    @RequestMapping(value="/member/signUp", method = RequestMethod.GET)
    public String signUp(){
        return "/member/signUp";
    }

    @RequestMapping(value="/member/changePw", method = RequestMethod.GET)
    public ModelAndView changePw(@AuthenticationPrincipal SecurityAccount account){
        ModelAndView mv = new ModelAndView("/member/changePw");
        mv.addObject("Id", account.getUsername());
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/member/changePw", method = RequestMethod.POST)
    public Boolean changePw(Account account, HttpServletRequest httpServletRequest){
        UserDetails user = accountService.loadUserByUsername(account.getId());

        // 입력한 비밀번호와 저장된 비밀번호 비교
        if(accountService.isSamePw(user.getPassword(), httpServletRequest.getParameter("ex_password"))){
            accountService.updatePw(account);
            return true;
        }
        //입력한 비밀번호 틀림
        else{
            return false;
        }
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
