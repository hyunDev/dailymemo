package org.hyunpro.webapp.dailymemo.board.service;

import java.util.List;

import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.entity.BoardFileEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface JpaBoardService {

    List<BoardEntity> selectBoardList() throws Exception;

    void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest, SecurityAccount account) throws Exception;

    void updateBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest, SecurityAccount account) throws Exception;

    BoardEntity selectBoardDetail(int boardIdx, String mode) throws Exception;

    void deleteBoard(int boardIdx);

    BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception;
}