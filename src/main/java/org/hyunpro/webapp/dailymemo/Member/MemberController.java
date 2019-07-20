package org.hyunpro.webapp.dailymemo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String registration(Model model) {
        return "member/signUp";
    }

    @PostMapping("")
    public String create(Member member) {
        MemberRole role = new MemberRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setUpw(passwordEncoder.encode(member.getUpw()));
        role.setRoleName("BASIC");
        member.setRoles(Arrays.asList(role));
        memberRepository.save(member);
        return "redirect:/";
    }
}
