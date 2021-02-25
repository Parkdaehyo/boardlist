package com.spring.mvc.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.mvc.board.model.BoardVO_forth;
import com.spring.mvc.board.model.BoardVO_third;
import com.spring.mvc.board.model.ImageVO;
import com.spring.mvc.board.model.ImageVO2;
import com.spring.mvc.board.repository.forth_IBoardMapper;
import com.spring.mvc.commons.SearchVO;

@Service
public class BoardService_comment {

	@Inject
	private forth_IBoardMapper mapper;
	

	//////////////////////////////////////////////////////////////////////////////////////////
	//다중 첨부파일
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public int insertNewArticle_commentboard(Map articleMap) throws DataAccessException {
		
		int boardNo = mapper.insertNewArticle_commentboard(articleMap);
		articleMap.put("boardNO" , boardNo);
		mapper.insertNewArticle_commentboard(articleMap);
		return boardNo;
	}
	
	public BoardVO_forth getArticle_commentboard(Integer boardNo, HttpServletRequest request, HttpServletResponse response) {
		
		
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
	                    //System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
	                    int limitTime = 300;
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
             newCookie.setMaxAge(300);
                             
             // 쿠키 추가
             response.addCookie(newCookie); 
             
     		mapper.updateViewCnt_commentboard(boardNo);
     		return mapper.getArticle_commentboard(boardNo);
     		
	        } else { //viewCookie가 있다면 여기로 빠지는데
	        	
	        	if (viewCookie.getMaxAge() < 1) {
	        	
	        	mapper.updateViewCnt_commentboard(boardNo);
	        	
	        	}
	        	
	        	return mapper.getArticle_commentboard(boardNo);
	    		
	        }
		
	}
	
	
	

	public void updateViewCnt_commentboard(Integer boardNo) {
		// TODO Auto-generated method stub
		
	}

	public void delete_commentboard(Integer boardNo) {
		mapper.delete_commentboard(boardNo);
		
	}

	public void updateArticle_commentboard(Map articleMap) throws DataAccessException {
		
		//articleMap을 넘겨서 정상적으로 데이터가 수정이된다.
		mapper.updateArticle_commentboard(articleMap); 

		List<ImageVO2> imageFileList = (ArrayList) articleMap.get("imageFileList");
		
		System.out.println("=========================");
		System.out.println(">size="+imageFileList.size());
	
		
		//imageFileList에 바뀐 imageFileName 이미지가 들어온다.
		
	
		for(ImageVO2 imageVO : imageFileList) {
			System.out.println(">> start for");
			
			if(imageVO.getImageFileName() != null) {
		
			  String imageFileName = imageVO.getImageFileName(); 
			  int boardNo = imageVO.getBoardNo();
			  int imageFileNO = imageVO.getImageFileNO();
		  		
			  System.out.println("DAO까지 파일이름이 넘어왔는가?: " + imageFileName);
			  System.out.println("DAO까지 글번호가 넘어왔는가?: " + boardNo); 
			  System.out.println("DAO까지 이미지번호가 넘어왔는가?: " + imageFileNO);

			  articleMap = imageFileList.stream()
				      	.collect(Collectors.groupingBy( arg -> arg, HashMap::new, Collectors.counting()));

			  
			  mapper.updateNewImage_commentboard(imageVO);
		  }
		
		}
	}
		
		
	
	public void update3(Map articleMap) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	public List<BoardVO_forth> getArticleList_commentboard(SearchVO search) {
			
			List<BoardVO_forth> list = mapper.getArticleList_commentboard(search);
		
		return list;
	}

	public Integer countArticles_commentboard(SearchVO search) {
	
		return mapper.countArticles_commentboard(search);
	}

	public Integer viewCount_commentboard(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer selectNewArticleNO_commentboard() {
		// TODO Auto-generated method stub
		return mapper.selectNewArticleNO_commentboard();
	}

	public int addNewArticle_commentboard(Map articleMap) throws Exception{
		
		
		int boardNo = mapper.selectNewArticleNO_commentboard();
	
		
		System.out.println("등록시 boardNo" + boardNo);
		
		articleMap.put("boardNo" , boardNo); 
		
		//왜 1이 리턴되는거지?
		int insertNewArticle3_boardNo = mapper.insertNewArticle_commentboard(articleMap); //boardNo가 계속 1만 셋팅되고 있는상황
		
		System.out.println("반환된 글번호" + insertNewArticle3_boardNo);
		
		List<ImageVO2> imageFileList = (List<ImageVO2>)articleMap.get("imageFileList");
		
		System.out.println("boarservice3의 articleMap" + articleMap);
		
		System.out.println("insertNewArticle3_boardNo" + insertNewArticle3_boardNo + "번");
		
		
		
		int board_no = (int) articleMap.get("boardNo");
		
		
		int imageFileNO = selectNewImageFileNO_commentboard(); //MAX값 가져오는것.
		
		/*
		 * if() {
		 * 
		 * }
		 */
		
		
		for(ImageVO2 imageVO : imageFileList){
			imageVO.setImageFileNO(++imageFileNO);
			imageVO.setBoardNo(board_no); 
			System.out.println("파일 이름:" + imageVO.getImageFileName());
		}
				
		return mapper.insertNewImage_commentboard(articleMap);
		

		
	}

	public int selectNewImageFileNO_commentboard() throws DataAccessException {
	
		return mapper.selectNewImageFileNO_commentboard();
	}

	
	
	public List <ImageVO2>selectImageFileList_commentboard(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectImageFileList_commentboard(articleNO);
	}

	
	
	
	// t_imageFile2 전체 조회
	public List selectImageFileList3_commentboard() throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectImageFileList3_commentboard();
	}
	
	
	
	

	public List<ImageVO2>selectImageFileNO_commentboard(int articleNO) throws DataAccessException {
	
		return mapper.selectImageFileNO_commentboard(articleNO);
	}
	
	
}
