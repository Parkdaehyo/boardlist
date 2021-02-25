package com.spring.mvc.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.model.BoardVO_third;
import com.spring.mvc.board.model.BoardVO_two;
import com.spring.mvc.board.model.ImageVO;
import com.spring.mvc.commons.SearchVO;

public interface IBoardService {

	
			/////////////////////////////////////////////////////////////////////////////////////
			// 다중 첨부파일
			/////////////////////////////////////////////////////////////////////////////////////
	
			public int addNewArticle3(Map articleMap) throws Exception;
			//글쓰기
			public int insertNewArticle3(Map articleMap) throws DataAccessException;
			//void insert(BoardVO aritcle);

			//게시글 단일 조회기능
			BoardVO_third getArticle3(Integer boardNo, HttpServletRequest request,HttpServletResponse response);
			
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
			
			int selectNewImageFileNO3() throws DataAccessException;
			
			//t_imageFile 리스트
			
			public List selectImageFileList(int articleNO) throws DataAccessException;
	
			//t_imageFile 테이블
			public List selectImageFileList3() throws DataAccessException;
			
			public List<ImageVO>selectImageFileNO(int articleNO) throws DataAccessException;
	
		
	
	
			/////////////////////////////////////////////////////////////////////////////////////
	
			/////////////////////////////////////////////////////////////////////////////////////
	
			//게시글 등록기능
			//public void insert(BoardVO article, MultipartHttpServletRequest mpRequest);
			void insert(BoardVO article);
			//public void insert(BoardVO article);
			
			//게시글 단일 조회기능
			BoardVO getArticle(Integer boardNo, HttpServletRequest request,HttpServletResponse response); //Integer == int
			
			//게시글 수정기능
			void update(BoardVO article);
					
			//게시글 삭제 기능
			void delete(Integer boardNo);
			
			// List<자료형> 변수명
			//게시글 목록 조회 기능(검색, 페이징 통합)
			List<BoardVO> getArticleList(SearchVO search);
			
			//글 갯수
			Integer countArticles(SearchVO search);
			
			Integer viewCount(int num);
	
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			//게시글 등록기능
			//public void insert(BoardVO article, MultipartHttpServletRequest mpRequest);
			public int addNewArticle(Map articleMap) throws Exception;
			//public void insert(BoardVO article);
			
			//게시글 단일 조회기능
			BoardVO_two getArticle2(Integer boardNo, HttpServletRequest request,HttpServletResponse response); //Integer == int
			
			// 글 + 첨부파일 수정 기능
			void updateArticle(Map articleMap) throws Exception;
			
			void update2(Map articleMap) throws Exception;
					
			//게시글 삭제 기능
			void delete2(Integer boardNo);
			
			// List<자료형> 변수명
			//게시글 목록 조회 기능(검색, 페이징 통합)
			List<BoardVO_two> getArticleList2(SearchVO search);
			
			//글 갯수
			Integer countArticles2(SearchVO search);
			
			Integer viewCount2(int num);
			int addNewArticle3(Map articleMap, HttpServletRequest request) throws Exception;
			
			
			
	
}
