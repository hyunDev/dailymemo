package org.hyunpro.webapp.dailymemo.diary.repository;

import org.hyunpro.webapp.dailymemo.diary.Entity.Diary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface diaryRepository extends CrudRepository<Diary, Integer> {

    @Query("SELECT diary.id, diary.date, diary.title, diary.contents FROM Diary diary WHERE diary.id = :id and diary.date like :date%")
    List<Object[]> findAllByIdAndDate(@Param("id") String id, @Param("date") String date);

    //List<Diary> findAllByIdAndDate(String id, String date);

}
