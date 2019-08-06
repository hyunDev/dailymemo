package org.hyunpro.webapp.dailymemo.board.board.repository;

import java.util.List;

import org.hyunpro.webapp.dailymemo.board.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.board.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer>{

    List<BoardEntity> findAllByOrderByBoardIdxDesc();

    @Query("SELECT file FROM BoardFileEntity file WHERE board_idx = :boardIdx AND idx = :idx")
    BoardFileEntity findBoardFile(@Param("boardIdx") int boardIdx, @Param("idx") int idx);
}
