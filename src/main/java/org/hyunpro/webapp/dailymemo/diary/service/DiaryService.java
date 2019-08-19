package org.hyunpro.webapp.dailymemo.diary.service;


import java.util.HashMap;

public interface DiaryService {

    HashMap<String, Integer> getDate() throws Exception;
    HashMap<String, Integer> getDate(int year, int month) throws Exception;
}
