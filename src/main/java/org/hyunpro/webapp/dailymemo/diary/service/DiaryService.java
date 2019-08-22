package org.hyunpro.webapp.dailymemo.diary.service;


import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.diary.Entity.Diary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public interface DiaryService {

    ArrayList<Diary> selectDiaryList(int year, int month, SecurityAccount account) throws Exception;
    HashMap<String, Integer> getDate() throws Exception;
    HashMap<String, Integer> getDate(int year, int month) throws Exception;
    Calendar getDate(int year, int month, int day) throws Exception;
    void saveDiary(Diary diary, SecurityAccount account) throws Exception;
}
