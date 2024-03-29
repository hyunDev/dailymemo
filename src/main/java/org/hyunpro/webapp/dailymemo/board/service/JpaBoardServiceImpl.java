package org.hyunpro.webapp.dailymemo.board.service;

import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.entity.BoardFileEntity;
import org.hyunpro.webapp.dailymemo.board.entity.Comment;
import org.hyunpro.webapp.dailymemo.board.repository.JpaBoardRepository;
import org.hyunpro.webapp.dailymemo.board.repository.JpaCommentRepository;
import org.hyunpro.webapp.dailymemo.board.repository.JpaReplyRepository;
import org.hyunpro.webapp.dailymemo.common.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class JpaBoardServiceImpl implements JpaBoardService{

    @Autowired
    JpaBoardRepository jpaBoardRepository;

    @Autowired
    JpaReplyRepository jpaReplyRepository;

    @Autowired
    JpaCommentRepository jpaCommentRepository;

    @Autowired
    FileUtils fileUtils;

    @Override
    public List<BoardEntity> selectBoardList() throws Exception {
        return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
    }

    @Override
    public void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest, SecurityAccount account) throws Exception {
        board.setCreatorId(account.getUsername());
        List<BoardFileEntity> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
        if(CollectionUtils.isEmpty(list) == false){
            board.setFileList(list);
        }
        jpaBoardRepository.save(board);
    }

    @Override
    public void saveBoard(BoardEntity board) throws Exception {
        jpaBoardRepository.save(board);
    }

    @Override
    public void updateBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest, SecurityAccount account) throws Exception {
        board.setCreatorId(account.getUsername());
        board.setUpdatedDatetime(LocalDateTime.now());


        jpaBoardRepository.update(board.getContents(), board.getTitle(), board.getUpdatedDatetime(), board.getBoardIdx(), account.getUsername());
    }

    @Override
    public void saveReply(Reply reply, SecurityAccount account) throws Exception {
        reply.setCreatorId(account.getUsername());

        jpaReplyRepository.save(reply);
    }

    @Override
    public void saveComment(Comment comment, SecurityAccount account) throws Exception {
        comment.setCreatorId(account.getUsername());

        jpaCommentRepository.save(comment);
    }

    @Override
    public List<Reply> getReplyList(int boardIdx) throws Exception {
        List<Reply> replyList = jpaReplyRepository.findAllById(boardIdx);
        return replyList;
    }

    @Override
    public List<Comment> getCommentList(int boardIdx) throws Exception {
        List<Comment> commentList = jpaCommentRepository.findAllById(boardIdx);
        return commentList;
    }

    @Override
    public BoardEntity selectBoardDetail(int boardIdx, String mode) throws Exception{
        Optional<BoardEntity> optional = jpaBoardRepository.findById(boardIdx);
        if(optional.isPresent()){
            BoardEntity board = optional.get();

            if(mode=="detail") { //  수정할때는 안타게
                board.setHitCnt(board.getHitCnt() + 1);
                jpaBoardRepository.save(board);
            }

            return board;
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteBoard(BoardEntity board, SecurityAccount account) {
        jpaBoardRepository.deleteById(board.getBoardIdx());
    }

    @Override
    public BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception {
        BoardFileEntity boardFile = jpaBoardRepository.findBoardFile(boardIdx, idx);
        return boardFile;
    }
}
