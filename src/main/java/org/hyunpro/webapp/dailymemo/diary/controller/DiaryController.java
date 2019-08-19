package org.hyunpro.webapp.dailymemo.diary.controller;

import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DiaryController {


    @RequestMapping(value="/layout/diary/diary", method=RequestMethod.GET)
    public ModelAndView openDiary(ModelMap model, @AuthenticationPrincipal SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView("/layout/diary/diary");

        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("MM yyyy");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        mv.addObject("month_year", date.format(d));
        mv.addObject("date", d);
        mv.addObject("year", year.format(d));
        mv.addObject("month", month.format(d));

        //List<BoardEntity> list = jpaBoardService.selectBoardList();
        //mv.addObject("list", list);
        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    @RequestMapping(value="/layout/diary/year/{year}/month/{month}", method=RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("year") int year, @PathVariable("month") int month, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView("/layout/diary/diary");

        mv.addObject("year", year);
        mv.addObject("month", month);

        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }
}
