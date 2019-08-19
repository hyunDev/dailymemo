package org.hyunpro.webapp.dailymemo.diary.controller;

import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.diary.service.DiaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
public class DiaryController {

    @Autowired
    DiaryServiceImpl diaryService;

    @RequestMapping(value="/layout/diary/diary", method=RequestMethod.GET)
    public ModelAndView openDiary(ModelMap model, @AuthenticationPrincipal SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView("/layout/diary/diary");

        HashMap<String, Integer> date = diaryService.getDate();

        mv.addObject("year", date.get("year"));
        mv.addObject("month", date.get("month"));
        mv.addObject("last_day", date.get("last_day"));
        mv.addObject("ex_last_day", date.get("ex_last_day"));

        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    @RequestMapping(value="/layout/diary/diary/{year}/{month}", method=RequestMethod.GET)
    public ModelAndView openDiary(@PathVariable("year") int year, @PathVariable("month") int month, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView("/layout/diary/diary");

        HashMap<String, Integer> date = diaryService.getDate(year, month);

        mv.addObject("year", date.get("year"));
        mv.addObject("month", date.get("month"));
        mv.addObject("last_day", date.get("last_day"));
        mv.addObject("ex_last_day", date.get("ex_last_day"));

        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }
}
