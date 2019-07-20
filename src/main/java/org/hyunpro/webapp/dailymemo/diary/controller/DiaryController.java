package org.hyunpro.webapp.dailymemo.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DiaryController {

    @RequestMapping(value="/diary", method= RequestMethod.GET)
    public ModelAndView openDiary() throws Exception{
        ModelAndView mv = new ModelAndView("/diary/diary");

        return mv;
    }
}
