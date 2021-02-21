package com.spring.mvc.board.repository;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.mvc.board.model.BoardVO_two;
import com.spring.mvc.commons.SearchVO;

public interface second_IBoardMapper {

	
	//글쓰기
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	//void insert(BoardVO aritcle);

	//게시글 단일 조회기능
	BoardVO_two getArticle2(Integer boardNo); //Integer == int
	
	//게시물 조회수 상승처리
	void updateViewCnt2(Integer boardNo);
	
	

	//게시글 삭제 기능
	void delete2(Integer boardNo);

	//게시글,첨부파일 수정
	public void updateArticle(Map articleMap) throws DataAccessException;
	
	public void update2(Map articleMap) throws DataAccessException;
	
	//# 검색, 페이지 기능이 포함된 게시글 목록 조회기능
	List<BoardVO_two> getArticleList2(SearchVO search);
	
	
	Integer countArticles2(SearchVO search);
	
	Integer viewCount2(int boardNo);
	
	Integer selectNewArticleNO();
	
}
