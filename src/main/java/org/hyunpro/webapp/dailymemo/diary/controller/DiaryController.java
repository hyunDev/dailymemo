package org.hyunpro.webapp.dailymemo.diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.diary.Entity.Diary;
import org.hyunpro.webapp.dailymemo.diary.service.DiaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.expression.Arrays;

import java.util.*;

@Controller
@Slf4j
@RequestMapping("/layout/diary")
public class DiaryController {

    private String basicUrl = "/layout/diary";
    @Autowired
    DiaryServiceImpl diaryService;

    @RequestMapping(value="/diary", method=RequestMethod.GET)
    public ModelAndView openDiary(ModelMap model, @AuthenticationPrincipal SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView(basicUrl + "/diary");

        HashMap<String, Integer> date = diaryService.getDate();

        ArrayList<Diary> calendar = diaryService.selectDiaryList(date.get("year"), date.get("month"), account);


        mv.addObject("year", date.get("year"));
        mv.addObject("calendar",calendar);
        mv.addObject("month", date.get("month"));
        mv.addObject("last_day", date.get("last_day"));
        mv.addObject("ex_last_day", date.get("ex_last_day"));

        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    @RequestMapping(value="/diary/{year}/{month}", method=RequestMethod.GET)
    public ModelAndView openDiary(@PathVariable("year") int year, @PathVariable("month") int month, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView(basicUrl + "/diary");

        HashMap<String, Integer> date = diaryService.getDate(year, month);

        mv.addObject("year", date.get("year"));
        mv.addObject("month", date.get("month"));
        mv.addObject("last_day", date.get("last_day"));
        mv.addObject("ex_last_day", date.get("ex_last_day"));

        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    @RequestMapping(value="/diaryWrite/{year}/{month}/{day}", method=RequestMethod.GET)
    public ModelAndView writeDiary(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView(basicUrl+ "/diaryWrite");

        Calendar d = diaryService.getDate(year, month, day);

        mv.addObject("date", d);

        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    //String으로 반환 로그인 상태 유지하는거 topbar에 보여주는거 어떻게 할까?
    @RequestMapping(value="/diaryWrite", method=RequestMethod.POST)
    public String writeDiary(Diary diary, @AuthenticationPrincipal  SecurityAccount account) throws Exception{

        diaryService.saveDiary(diary ,account);

        return "redirect:" + basicUrl + "/diary";
    }
}
