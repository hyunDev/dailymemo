package org.hyunpro.webapp.dailymemo.board.board.service;

import java.util.List;

import org.hyunpro.webapp.dailymemo.board.board.dto.BoardDto;
import org.hyunpro.webapp.dailymemo.board.board.dto.BoardFileDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface BoardService {
	List<BoardDto> selectBoardList() throws Exception;
	void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	void updateBoard(BoardDto board) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
	BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception;
}
