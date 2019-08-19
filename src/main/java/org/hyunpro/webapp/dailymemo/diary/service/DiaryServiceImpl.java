package org.hyunpro.webapp.dailymemo.diary.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Override
    public HashMap<String, Integer> getDate() throws Exception {
        HashMap<String, Integer> calendar = new HashMap<>();

        Date d = new Date();

        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");

        calendar.put("year", Integer.parseInt(year.format(d)));
        calendar.put("month", Integer.parseInt(month.format(d)));
        calendar.put("last_day", Integer.parseInt(month.format(d)));
        calendar.put("ex_last_day", Integer.parseInt(month.format(d)));

        return calendar;
    }

    @Override
    public HashMap<String, Integer> getDate(int year, int month) throws Exception {
        HashMap<String, Integer> calendar = new HashMap<>();

        //화살표를 눌러서 변한 달이 0 또는 13이 되었을 때
        if(month == 0){
            year = year - 1;
            month = 12;
        }

        if(month == 13){
            year = year + 1;
            month = 1;
        }

        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, 1); // 현재 년월 지정

        calendar.put("year", year);
        calendar.put("month", month);
        calendar.put("last_day", cal.getActualMaximum(month)); //현재 년월 마지막 날짜

        if(month == 1){ // 1월달 이전달은 12월
            year = year - 1;
            month = 12;
        }
        else{
            month = month - 1;
        }

        cal.set(year, month-1, 1); // 이전달 지정

        calendar.put("ex_last_day", cal.getActualMaximum(month)); // 이전달 마지막 날짜

        return calendar;
    }
}
