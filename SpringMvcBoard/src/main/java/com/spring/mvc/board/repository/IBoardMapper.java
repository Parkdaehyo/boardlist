package com.spring.mvc.board.repository;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.commons.SearchVO;

public interface IBoardMapper {

	
	
	void insert(BoardVO article);
	//void insert(BoardVO aritcle);

	//게시글 단일 조회기능
	BoardVO getArticle(Integer boardNo); //Integer == int
	
	//게시물 조회수 상승처리
	void updateViewCnt(Integer boardNo);
	
	//게시글 수정기능
	void update(BoardVO article);

	//게시글 삭제 기능
	void delete(Integer boardNo);
	

	//# 검색, 페이지 기능이 포함된 게시글 목록 조회기능
	List<BoardVO> getArticleList(SearchVO search);
	
	
	Integer countArticles(SearchVO search);
	
	Integer viewCount(int boardNo);
	
	
}
