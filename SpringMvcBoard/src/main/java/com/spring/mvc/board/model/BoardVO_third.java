package com.spring.mvc.board.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class BoardVO_third {

	
	private Integer boardNo;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Integer viewCnt;
	private String imageFileName;
	
	
	public Integer getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title.replaceAll("(?i)<script", "&lt;script");
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content.replaceAll("(?i)<script", "&lt;script");
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer.replaceAll("(?i)<script", "&lt;script");
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Integer getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		try {
			if(imageFileName!= null && imageFileName.length()!=0) {
				this.imageFileName = URLEncoder.encode(imageFileName,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	
	
	
}
