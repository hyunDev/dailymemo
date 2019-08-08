package org.hyunpro.webapp.dailymemo.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.hyunpro.webapp.dailymemo.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.service.JpaBoardService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value="/jpa/board", method=RequestMethod.GET)
    public ModelAndView openBoardList(ModelMap model) throws Exception{
        ModelAndView mv = new ModelAndView("/board/jpaBoardList");

        List<BoardEntity> list = jpaBoardService.selectBoardList();
        mv.addObject("list", list);

        return mv;
    }

    @RequestMapping(value="/jpa/board/test", method=RequestMethod.GET)
    public String test() throws Exception{
        return "/board/test";
    }

    @RequestMapping(value="/jpa/board/write", method=RequestMethod.GET)
    public String openBoardWrite() throws Exception{
        return "/board/jpaBoardWrite";
    }

    @RequestMapping(value="/jpa/board/write", method=RequestMethod.POST)
    public String writeBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
        jpaBoardService.saveBoard(board, multipartHttpServletRequest);
        return "redirect:/jpa/board";
    }

    @RequestMapping(value="/jpa/board/{boardIdx}", method=RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception{
        ModelAndView mv = new ModelAndView("/board/jpaBoardDetail");

        BoardEntity board = jpaBoardService.selectBoardDetail(boardIdx);
        mv.addObject("board", board);

        return mv;
    }

    @RequestMapping(value="/jpa/board/{boardIdx}", method=RequestMethod.PUT)
    public String updateBoard(BoardEntity board) throws Exception{
        jpaBoardService.saveBoard(board, null);
        return "redirect:/jpa/board";
    }

    @RequestMapping(value="/jpa/board/{boardIdx}", method=RequestMethod.DELETE)
    public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception{
        jpaBoardService.deleteBoard(boardIdx);
        return "redirect:/jpa/board";
    }

    @RequestMapping(value="/jpa/board/file", method=RequestMethod.GET)
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