package com.spring.mvc.board.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/*
	 * 		
		Cookie[] getCookie = request.getCookies();
	
		//쿠키가 존재하는경우 
		if(getCookie != null){ // 만약 쿠키가 없으면 쿠키 생성 , 쿠키값이 존재하면 조회수 증가가 되면 안된다. null이 아니란 얘기 == 쿠키값이 존재한다.

			for(int i=0; i< getCookie.length; i++){

			Cookie c = getCookie[i]; // 객체 생성

			String name = c.getName(); // 쿠키 이름 가져오기

			String value = c.getValue(); // 쿠키 값 가져오기

			}
		
			return mapper.getArticle(boardNo);

		} else {
			
			mapper.updateViewCnt(boardNo);
			return mapper.getArticle(boardNo);
			
		}
		
	 * 
	 */
	
	@Override
	public BoardVO getArticle(Integer boardNo, HttpServletRequest request, HttpServletResponse response) {
	

		   Cookie[] cookies = request.getCookies(); //모든 ㅋ
	        
	        // 비교하기 위해 새로운 쿠키
	        Cookie viewCookie = null;
	      
	        // 쿠키가 있을 경우 
	        if (cookies != null && cookies.length > 0) 
	        {
	            for (int i = 0; i < cookies.length; i++)
	            {
	                // Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌 
	                if (cookies[i].getName().equals("cookie"+boardNo))
	                { 
	                    System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
	                    int limitTime = 10;
	                   viewCookie = cookies[i];
	                   viewCookie.setMaxAge(limitTime);
	                }
	            }
	        }
	        
	        //처음에는 무조건 여기로 떨어지겠지?
	        if (viewCookie == null) {  //쿠키가없어야만 조회수 증가로직 처리
                System.out.println("cookie 없음");
                
              
                // 쿠키 생성(이름, 값)
                Cookie newCookie = new Cookie("cookie"+boardNo, "|" + boardNo + "|");
                newCookie.setMaxAge(10);
                                
                // 쿠키 추가
                response.addCookie(newCookie); 
                
        		mapper.updateViewCnt(boardNo);
        		return mapper.getArticle(boardNo);
        		
	        } else { //viewCookie가 있다면 여기로 빠지는데
	        	
	        	if (viewCookie.getMaxAge() < 1) {
	        	
	        	mapper.updateViewCnt(boardNo);
	        	
	        	}
	        	
	        	return mapper.getArticle(boardNo);
	    		
	        }
		

		

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
