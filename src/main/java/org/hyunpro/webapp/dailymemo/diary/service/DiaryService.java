package org.hyunpro.webapp.dailymemo.diary.service;


import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.diary.Entity.Diary;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public interface DiaryService {

    List<Object[]> selectDiaryList(int year, int month, SecurityAccount account) throws Exception;
    HashMap<String, Integer> getDate() throws Exception;
    HashMap<String, Integer> getDate(int year, int month) throws Exception;
    Calendar getDate(int year, int month, int day) throws Exception;
    void saveDiary(Diary diary, SecurityAccount account) throws Exception;
}
