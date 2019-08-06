package org.hyunpro.webapp.dailymemo.board.board.service;

import java.util.List;

import org.hyunpro.webapp.dailymemo.board.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.board.entity.BoardFileEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface JpaBoardService {

    List<BoardEntity> selectBoardList() throws Exception;

    void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    BoardEntity selectBoardDetail(int boardIdx) throws Exception;

    void deleteBoard(int boardIdx);

    BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception;
}