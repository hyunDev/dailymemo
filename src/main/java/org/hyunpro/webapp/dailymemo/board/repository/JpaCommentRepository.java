package org.hyunpro.webapp.dailymemo.board.repository;

import org.hyunpro.webapp.dailymemo.board.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaCommentRepository extends CrudRepository<Comment, Integer> {

    @Query(value = "SELECT c FROM Comment c where board_idx = :boardIdx")
    List<Comment> findAllById(@Param("boardIdx") int boardIdx);

}
