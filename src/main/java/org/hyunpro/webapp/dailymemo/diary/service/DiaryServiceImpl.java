package org.hyunpro.webapp.dailymemo.diary.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DiaryServiceImpl implements DiaryService {

    @Override
    public HashMap<String, Integer> getDate() throws Exception {
        HashMap<String, Integer> calendar = new HashMap<>();

        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("MM yyyy");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");

        return calendar;
    }

    @Override
    public HashMap<String, Integer> getDate(int year, int month) throws Exception {
        HashMap<String, Integer> calendar = new HashMap<>();

        Calendar cal = Calendar.getInstance();

        cal.set(year, month-1);

        calendar.put("lastDay", cal.getActualMaximum(month));

        if(month == 1){
            year = year - 1;
            month = 12;
        }
        else{
            month = month - 1;
        }


        return calendar;
    }
}
