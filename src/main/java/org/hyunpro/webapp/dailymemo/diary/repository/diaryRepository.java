package org.hyunpro.webapp.dailymemo.diary.repository;

import org.hyunpro.webapp.dailymemo.diary.Entity.Diary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.thymeleaf.expression.Arrays;

import java.sql.Array;
import java.util.ArrayList;


public interface diaryRepository extends CrudRepository<Diary, Integer> {

    /*
    @Query(value = "SELECT * FROM Diary diary RIGHT OUTER JOIN( " +
            "SELECT @i\\:=DATE_ADD(@i, INTERVAL 1 DAY) DATE " +
            "FROM  NUMBER, " +
            "(SELECT @i\\:=DATE(:date)) v " +
            "WHERE @i < DATE_ADD(DATE(:date), INTERVAL 42 DAY)" +
            ") generator ON DATE(diary.date) = generator.DATE " +
            "AND diary.id =:id" , nativeQuery = true)
    ArrayList<Diary> findAllByIdAndDate(@Param("id") String id, @Param("date") String date);
    */

    @Query(value = "SELECT * FROM ( " +
            "SELECT @i\\:=DATE_ADD(@i, INTERVAL 1 DAY) DATES " +
            "FROM  NUMBER, (SELECT @i\\:=DATE(:date)) v " +
            "WHERE @i < DATE_ADD(DATE(:date), INTERVAL 42 DAY)) generator " +
            "LEFT JOIN Diary diary ON DATE(diary.date) = generator.DATES AND diary.id =:id" , nativeQuery = true)
    ArrayList<Diary> findAllByIdAndDate(@Param("id") String id, @Param("date") String date);


    /*@Query("SELECT diary FROM Diary diary WHERE diary.id = :id and diary.date like :date%")
    List<Diary> findAllByIdAndDate(@Param("id") String id, @Param("date") String date);*/


}
