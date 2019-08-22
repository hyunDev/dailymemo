package org.hyunpro.webapp.dailymemo.diary.service;

import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.diary.Entity.Diary;
import org.hyunpro.webapp.dailymemo.diary.repository.diaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.thymeleaf.expression.Arrays;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DiaryServiceImpl implements DiaryService {

    // cal.add(Calendar.DATE, 3);  //일 3증가

    @Autowired
    private diaryRepository diaryRepository;

    @Override
    public ArrayList<Diary> selectDiaryList(int year, int month, SecurityAccount account) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, 1); // 페이지 달의 1일
        cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK)); // 일요일 부터 시작하는 날짜로 돌아감

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return diaryRepository.findAllByIdAndDate(account.getUsername(), dateFormat.format(cal.getTime()));
    }

    @Override
    public HashMap<String, Integer> getDate() throws Exception {
        HashMap<String, Integer> calendar = new HashMap<>();

        Date d = new Date();
        Calendar cal = Calendar.getInstance();


        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");

        int year = Integer.parseInt(yearFormat.format(d));
        int month = Integer.parseInt(monthFormat.format(d));

        calendar.put("year", year);
        calendar.put("month", month);

        cal.set(year, month-1, 1); // 현재 년월 지정 cal.set에서 month가 0이 1월
        calendar.put("last_day", cal.getActualMaximum(Calendar.DAY_OF_MONTH)); //현재 년월 마지막 날짜
        //System.out.println("------이번달 마지막 날짜:::" + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        if(month == 1){ // 1월달 이전달은 12월
            year = year - 1;
            month = 12;
        }
        else{
            month = month - 1;
        }

        cal.set(year, month-1, 1); // 이전달  지정
        calendar.put("ex_last_day", cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // 이전달 마지막 날짜

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
        calendar.put("last_day", cal.getActualMaximum(Calendar.DAY_OF_MONTH)); //현재 년월 마지막 날짜

        if(month == 1){ // 1월달 이전달은 12월
            year = year - 1;
            month = 12;
        }
        else{
            month = month - 1;
        }

        cal.set(year, month-1, 1); // 이전달 지정

        calendar.put("ex_last_day", cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // 이전달 마지막 날짜

        //System.out.println("------이전달 마지막 날짜:::" + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    @Override
    public Calendar getDate(int year, int month, int day) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day);
        return cal;
    }

    @Override
    public void saveDiary(Diary diary, SecurityAccount account) throws Exception {
        diary.setId(account.getUsername());
        System.out.println(diary.getId() + "---" +diary.getDate());
        diaryRepository.save(diary);
    }
}
