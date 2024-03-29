package org.hyunpro.webapp.dailymemo.board.controller;

import org.apache.commons.io.FileUtils;
import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.entity.BoardFileEntity;
import org.hyunpro.webapp.dailymemo.board.entity.Comment;
import org.hyunpro.webapp.dailymemo.board.service.JpaBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;


@Controller
public class JpaBoardController {

    private String basicUrl = "/layout/board";

    @Autowired
    private JpaBoardService jpaBoardService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView openIndex(@AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView(basicUrl + "/jpaboardList");

        List<BoardEntity> list = jpaBoardService.selectBoardList();
        mv.addObject("list", list);

        if(account != null)
            mv.addObject("Id", account.getUsername());
        return mv;
    }

    @RequestMapping(value="/layout/board/list", method=RequestMethod.GET)
    public ModelAndView openBoardList(@AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView(basicUrl + "/jpaboardList");

        List<BoardEntity> list = jpaBoardService.selectBoardList();
        mv.addObject("list", list);

        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    @RequestMapping(value="/layout/board/write", method=RequestMethod.GET)
    public ModelAndView openBoardWrite(@AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView(basicUrl + "/jpaBoardWrite");
        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    @RequestMapping(value="/layout/board/write", method=RequestMethod.POST)
    public String writeBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        jpaBoardService.saveBoard(board, multipartHttpServletRequest, account);
        return "redirect:" + basicUrl + "/list";
    }


    @ResponseBody
    @RequestMapping(value="/layout/board/insertComment", method = RequestMethod.POST)
    public String insertComment(@ModelAttribute Comment comment, @RequestParam int board_idx, @AuthenticationPrincipal  SecurityAccount account) throws Exception{

        BoardEntity board = jpaBoardService.selectBoardDetail(board_idx, "comment");
        comment.setCreatorId(account.getUsername());
        board.addComment(comment);
        jpaBoardService.saveBoard(board);

        return "true";
    }

    @RequestMapping(value="/layout/board/detail/{boardIdx}", method=RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView(basicUrl + "/jpaBoardDetail");

        BoardEntity board = jpaBoardService.selectBoardDetail(boardIdx, "detail");
        //List<Reply> list = jpaBoardService.getReplyList(boardIdx);
        List<Comment> list = jpaBoardService.getCommentList(boardIdx);
        mv.addObject("board", board);
        mv.addObject("list", list);

        if(account != null)
            mv.addObject("Id", account.getUsername());
        return mv;
    }

    @RequestMapping(value="/layout/board/modify/{boardIdx}", method=RequestMethod.GET)
    public ModelAndView openBoardModify(@PathVariable("boardIdx") int boardIdx, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView(basicUrl + "/jpaBoardModify");

        BoardEntity board = jpaBoardService.selectBoardDetail(boardIdx, "modify");
        mv.addObject("board", board);
        if(account != null)
            mv.addObject("Id", account.getUsername());
        return mv;
    }

    @RequestMapping(value="/layout/board/modify", method=RequestMethod.POST)
    public String updateBoard(BoardEntity board,  @AuthenticationPrincipal SecurityAccount account) throws Exception{
        jpaBoardService.updateBoard(board, null, account);
        return "redirect:/layout/board/list";
    }

    @RequestMapping(value="/layout/board/delete", method=RequestMethod.GET)
    public String deleteBoard(BoardEntity board,  @AuthenticationPrincipal SecurityAccount account) throws Exception{
        jpaBoardService.deleteBoard(board,account);
        return "redirect:/layout/board/list";
    }

    @RequestMapping(value="/layout/board/file", method=RequestMethod.GET)
    public void downloadBoardFile(int boardIdx, int idx, HttpServletResponse response) throws Exception{
        BoardFileEntity file = jpaBoardService.selectBoardFileInformation(boardIdx, idx);

        byte[] files = FileUtils.readFileToByteArray(new File(file.getStoredFilePath()));

        response.setContentType("application/octet-stream");
        response.setContentLength(files.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(file.getOriginalFileName(),"UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(files);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}