package com.spring.mvc.board.repository;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;


import com.spring.mvc.board.model.BoardVO_third;
import com.spring.mvc.board.model.ImageVO;
import com.spring.mvc.commons.SearchVO;

public interface third_IBoardMapper {

		
		//Junit 테스트용
		void insert3(BoardVO_third article);
		//글쓰기
		public int insertNewArticle3(Map articleMap) throws DataAccessException;
		//void insert(BoardVO aritcle);

		//게시글 단일 조회기능
		BoardVO_third getArticle3(Integer boardNo); //Integer == int
		
		//게시물 조회수 상승처리
		void updateViewCnt3(Integer boardNo);
		
	
		//게시글 삭제 기능
		void delete3(Integer boardNo);

		//게시글,첨부파일 수정
		public void updateArticle3(Map articleMap) throws DataAccessException;
		
		public void update3(Map articleMap) throws DataAccessException;
		
		//# 검색, 페이지 기능이 포함된 게시글 목록 조회기능
		List<BoardVO_third> getArticleList3(SearchVO search);
		
		
		Integer countArticles3(SearchVO search);
		
		Integer viewCount3(int boardNo);
		
		Integer selectNewArticleNO3();
	
		public int insertNewImage3(Map articleMap) throws DataAccessException;
		
		int selectNewImageFileNO3();
		
		public List <ImageVO>selectImageFileList(int articleNO) throws DataAccessException;
				
		public List selectImageFileList3() throws DataAccessException;
		
		public List <ImageVO>selectImageFileNO(int boardNo) throws DataAccessException;
		
		//다중 이미지 수정문
		public void updateNewImage(Map articleMap) throws DataAccessException;
		
}
