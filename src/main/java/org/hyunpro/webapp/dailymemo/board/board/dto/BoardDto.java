package org.hyunpro.webapp.dailymemo.board.board.dto;

import java.util.List;

import lombok.Data;

import javax.persistence.*;

@Data
public class BoardDto {

	private int boardIdx;

	private String title;

	private String contents;

	private int hitCnt;

	private String creatorId;

	private String createdDatetime;

	private String updaterId;

	private String updatedDatetime;

	private List<BoardFileDto> fileList;
}
