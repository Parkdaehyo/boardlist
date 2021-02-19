package com.spring.mvc.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.model.BoardVO_two;
import com.spring.mvc.commons.SearchVO;



public interface IBoardService {

	
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
			
			//게시글 수정기능
			void update2(BoardVO_two article);
					
			//게시글 삭제 기능
			void delete2(Integer boardNo);
			
			// List<자료형> 변수명
			//게시글 목록 조회 기능(검색, 페이징 통합)
			List<BoardVO_two> getArticleList2(SearchVO search);
			
			//글 갯수
			Integer countArticles2(SearchVO search);
			
			Integer viewCount2(int num);
			
			
			
	
}
