package com.spring.mvc.board.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;

import org.springframework.stereotype.Service;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.SearchVO;

@Service
public class BoardService implements IBoardService {

	
	@Inject
	private IBoardMapper mapper;

	@Override
	public void insert(BoardVO article) {
			
		System.out.println("Debug "  + article.toString());
		//System.out.println("Debug "  + mpRequest.getMultiFileMap().toString());
		
				 mapper.insert(article);
				 
				 
				 /*
				 List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(article, mpRequest); 
					int size = list.size();
					for(int i=0; i<size; i++){ 
						list.get(i); 
						mapper.insertFile(size);
					}*/
				 
				 
			}

	@Override
	public BoardVO getArticle(Integer boardNo) {
	
		
		
		mapper.updateViewCnt(boardNo);
		return mapper.getArticle(boardNo);
	}

	@Override
	public void update(BoardVO article) {
		mapper.update(article);
		
	}

	@Override
	public void delete(Integer boardNo) {
		mapper.delete(boardNo);
		
	}

	@Override
	public List<BoardVO> getArticleList(SearchVO search) {
		
		List<BoardVO> list = mapper.getArticleList(search);
		
		
		return list;
	}

	
	@Override
	public Integer countArticles(SearchVO search) {
		
		return mapper.countArticles(search);
	}

	@Override
	public Integer viewCount(int boardNo) {
		//
		return mapper.viewCount(boardNo);
	}
	
	
	
	
	
	
	
	
	
}
