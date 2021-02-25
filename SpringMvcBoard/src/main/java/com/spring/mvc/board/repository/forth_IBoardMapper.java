package com.spring.mvc.board.repository;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.mvc.board.model.BoardVO_forth;
import com.spring.mvc.board.model.BoardVO_third;
import com.spring.mvc.board.model.ImageVO;
import com.spring.mvc.board.model.ImageVO2;
import com.spring.mvc.commons.SearchVO;

public interface forth_IBoardMapper {

		
		//Junit 테스트용
		void insert_commentboard(BoardVO_forth article);
		//글쓰기
		public int insertNewArticle_commentboard(Map articleMap) throws DataAccessException;
		//void insert(BoardVO aritcle);

		//게시글 단일 조회기능
		BoardVO_forth getArticle_commentboard(Integer boardNo); //Integer == int
		
		//게시물 조회수 상승처리
		void updateViewCnt_commentboard(Integer boardNo);
		
	
		//게시글 삭제 기능
		void delete_commentboard(Integer boardNo);

		//게시글,첨부파일 수정
		public void updateArticle_commentboard(Map articleMap) throws DataAccessException;
		
		public void update_commentboard(Map articleMap) throws DataAccessException;
		
		//# 검색, 페이지 기능이 포함된 게시글 목록 조회기능
		List<BoardVO_forth> getArticleList_commentboard(SearchVO search);
		
		
		Integer countArticles_commentboard(SearchVO search);
		
		Integer viewCount_commentboard(int boardNo);
		
		Integer selectNewArticleNO_commentboard();
	
		public int insertNewImage_commentboard(Map articleMap) throws DataAccessException;
		
		Integer selectNewImageFileNO_commentboard();
		
		public List <ImageVO2>selectImageFileList_commentboard(int articleNO) throws DataAccessException;
				
		public List selectImageFileList3_commentboard() throws DataAccessException;
		
		
		//imageFileNO 가져온다
		public List <ImageVO2>selectImageFileNO_commentboard(int boardNo) throws DataAccessException;
		
		//다중 이미지 수정문
		public void updateNewImage_commentboard(ImageVO2 imagevo) throws DataAccessException;
		
}
