package org.hyunpro.webapp.dailymemo.board.service;

import java.util.List;

import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.entity.BoardFileEntity;
import org.hyunpro.webapp.dailymemo.board.entity.Comment;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface JpaBoardService {

    List<BoardEntity> selectBoardList() throws Exception;

    void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest, SecurityAccount account) throws Exception;
    void saveBoard(BoardEntity board) throws Exception;

    void updateBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest, SecurityAccount account) throws Exception;

    void saveComment(Comment comment, SecurityAccount account) throws Exception;

    void saveReply(Reply reply, SecurityAccount account) throws Exception;

    List<Reply> getReplyList(int boardIdx) throws Exception;

    List<Comment> getCommentList(int boardIdx) throws  Exception;

    BoardEntity selectBoardDetail(int boardIdx, String mode) throws Exception;

    void deleteBoard(BoardEntity board, SecurityAccount account);

    BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception;
}