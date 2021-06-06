package com.spring.mvc.board.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

public class ImageVO {

	
	private int imageFileNO;
	private String imageFileName;
	private Date regDate;
	private int boardNo;
	
	private String originImageFileName;
	
	
	
	public int getImageFileNO() {
		return imageFileNO;
	}
	public void setImageFileNO(int imageFileNO) {
		this.imageFileNO = imageFileNO;
	}
	
	public String getImageFileName() {
		try {
		if (imageFileName != null && imageFileName.length() != 0) {
		imageFileName = URLDecoder.decode(imageFileName, "UTF-8");
		}
		} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
		}
		return imageFileName;
		}

		public void setImageFileName(String imageFileName) {
		try {
		this.imageFileName = URLEncoder.encode(imageFileName, "UTF-8");//파일이름에 특수문자가 있을 경우 인코딩합니다.
		} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
		}
		}
		
	public String getOriginImageFileName() {
		return originImageFileName;
	}
	public void setOriginImageFileName(String originImageFileName) {
		this.originImageFileName = originImageFileName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	
	
	
}
