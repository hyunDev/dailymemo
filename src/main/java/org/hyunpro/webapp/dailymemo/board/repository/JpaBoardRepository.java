package org.hyunpro.webapp.dailymemo.board.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hyunpro.webapp.dailymemo.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer>{

    List<BoardEntity> findAllByOrderByBoardIdxDesc();

    @Query("SELECT file FROM BoardFileEntity file WHERE board_idx = :boardIdx AND idx = :idx")
    BoardFileEntity findBoardFile(@Param("boardIdx") int boardIdx, @Param("idx") int idx);

    @Modifying
    @Query(value="UPDATE t_jpa_board SET contents=:contents, title=:title, updated_datetime=:updated_datetime WHERE board_idx =:board_idx and creator_id = :id"
    , nativeQuery = true)
    void update(@Param("contents") String contents, @Param("title") String title
            , @Param("updated_datetime") LocalDateTime updated_datetime, @Param("board_idx") int board_idx, @Param("id") String id);
}
