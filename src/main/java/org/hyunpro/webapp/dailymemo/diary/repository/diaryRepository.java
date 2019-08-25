package org.hyunpro.webapp.dailymemo.diary.repository;

import org.hyunpro.webapp.dailymemo.diary.Entity.Diary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;


public interface diaryRepository extends CrudRepository<Diary, Integer> {

    @Query(value = "SELECT * FROM ( " +
            "SELECT @i\\:=DATE_ADD(@i, INTERVAL 1 DAY) DATES " +
            "FROM  NUMBER, (SELECT @i\\:=DATE(:date)) v " +
            "WHERE @i < DATE_ADD(DATE(:date), INTERVAL 42 DAY)) generator " +
            "LEFT JOIN Diary diary ON DATE(diary.date) = generator.DATES AND diary.id =:id" , nativeQuery = true)
    ArrayList<Diary> findAllByIdAndDate(@Param("id") String id, @Param("date") String date);

    @Query("SELECT d FROM Diary d WHERE d.id = :id AND d.date = :date")
    Diary findByIdAndDate(@Param("id") String id, @Param("date") String date);

}
