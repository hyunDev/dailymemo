package org.hyunpro.webapp.dailymemo.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.hyunpro.webapp.dailymemo.Account.Account;
import org.hyunpro.webapp.dailymemo.Account.AccountService;
import org.hyunpro.webapp.dailymemo.Account.SecurityAccount;
import org.hyunpro.webapp.dailymemo.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.service.JpaBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import org.hyunpro.webapp.dailymemo.board.entity.BoardFileEntity;


@Controller
public class JpaBoardController {

    @Autowired
    private JpaBoardService jpaBoardService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView openIndex(@AuthenticationPrincipal  SecurityAccount account){
        ModelAndView mv = new ModelAndView("/index");

        if(account != null)
            mv.addObject("Id", account.getUsername());
        return mv;
    }

    @RequestMapping(value="/layout/diary/diary", method=RequestMethod.GET)
    public ModelAndView openDiary(ModelMap model, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView("/layout/diary/diary");

      //List<BoardEntity> list = jpaBoardService.selectBoardList();
      //mv.addObject("list", list);
        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    @RequestMapping(value="/layout/board/list", method=RequestMethod.GET)
    public ModelAndView openBoardList(ModelMap model, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView("/layout/board/jpaboardList");

        List<BoardEntity> list = jpaBoardService.selectBoardList();
        mv.addObject("list", list);

        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    @RequestMapping(value="/layout/board/write", method=RequestMethod.GET)
    public ModelAndView openBoardWrite(@AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView("/layout/board/jpaBoardWrite");
        if(account != null)
            mv.addObject("Id", account.getUsername());

        return mv;
    }

    @RequestMapping(value="/layout/board/write", method=RequestMethod.POST)
    public String writeBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
        jpaBoardService.saveBoard(board, multipartHttpServletRequest);
        return "redirect:/layout/board/list";
    }

    @RequestMapping(value="/layout/board/{boardIdx}", method=RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx, @AuthenticationPrincipal  SecurityAccount account) throws Exception{
        ModelAndView mv = new ModelAndView("/board/jpaBoardDetail");

        BoardEntity board = jpaBoardService.selectBoardDetail(boardIdx);
        mv.addObject("board", board);
        if(account != null)
            mv.addObject("Id", account.getUsername());
        return mv;
    }

    @RequestMapping(value="/layout/board/{boardIdx}", method=RequestMethod.PUT)
    public String updateBoard(BoardEntity board) throws Exception{
        jpaBoardService.saveBoard(board, null);
        return "redirect:/layout/board";
    }

    @RequestMapping(value="/layout/board/{boardIdx}", method=RequestMethod.DELETE)
    public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception{
        jpaBoardService.deleteBoard(boardIdx);
        return "redirect:/layout/board";
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