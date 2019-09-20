package org.hyunpro.webapp.dailymemo.board.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaReplyRepository extends CrudRepository<Reply, Integer> {

    @Query(value = "SELECT r FROM Reply r where bno = :boardIdx")
    List<Reply> findAllById(@Param("boardIdx") int boardIdx);
}
