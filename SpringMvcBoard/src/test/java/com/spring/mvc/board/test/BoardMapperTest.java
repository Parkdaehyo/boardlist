package com.spring.mvc.board.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.model.BoardVO_third;
import com.spring.mvc.board.model.BoardVO_two;
import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.board.repository.second_IBoardMapper;
import com.spring.mvc.board.repository.third_IBoardMapper;


//메이븐 업데이트를 해야 pom.xml에 있는 dependency들이 제대로 반영된다.
// 매퍼의 빈이 등록되어있는 경로를 적어줘서 테스트 하도록 하기
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardMapperTest {

	@Autowired
	private third_IBoardMapper mapper;
	
	//게시글 등록 단위 테스트 //이게 단순한 테스트가 아니라 정말로 데이터베이스와 연결해서 작업을 수행하는 파일이다.
	@Test
	public void insertTest() {
		
	for (int i =1; i<=50; i++) {
	BoardVO_third article = new BoardVO_third();
	article.setTitle("다중 첨부파일 테스트 제목입니다.");
	article.setWriter("다중 첨부파일");
	article.setContent("다중 첨부파일 테스트 내용입니다");
	//article.setImageFileName1(null);
	//article.setImageFileName2(null);
	//article.setImageFileName3(null);

	mapper.insert3(article); //Autowired가 없으면 이게 안되는거같음.
	
	}
	System.out.println("게시물 등록 성공!");
	
	}
	
	@Test
	public void getListTest() {
		
		//List<BoardVO> list = mapper.getArticleList();
		
		//for(BoardVO vo : list) {
			//System.out.println(vo);
		//}
		
		//mapper.getArticleList().forEach(vo -> System.out.println(vo));
		
	}
	
	//게시글 단일 조회 테스트
	@Test
	public void getArticleTest() {
		
		//BoardVO article = mapper.getArticle(44);
		//System.out.println(article);
		
	}
	
	//게시글 수정 테스트
	@Test
	public void updateTest() {
		
		BoardVO article = new BoardVO();
		article.setBoardNo(1);
		article.setTitle("수정된 제목 이름!!");
		article.setContent("수정 테스트중!!!");
		//mapper.update(article);
		//System.out.println("수정 후 정보: " + mapper.getArticle(1));
		
	}
	
	//게시글 삭제 테스트
	@Test
	public void deleteTest() {
		//mapper.delete(3);
		//BoardVO vo = mapper.getArticle(3);
		//if(vo == null) {
			System.out.println(" # 게시물이 없습니다!");
		//}else {
		//	System.out.println(" # 게시물 정보: " + vo);
		//}
	
	}
	/*
	 * //페이징 단위 테스트
	 * 
	 * @Test public void pagingTest() {
	 * System.out.println("-------------------------"); PageVO paging = new
	 * PageVO(); paging.setPage(0); paging.setCountPerPage(20);
	 * 
	 * //mapper.getArticleListPaging(paging) //.forEach(vo ->
	 * System.out.println(vo)); System.out.println("---------------------------");
	 * 
	 * 
	 * }
	 */
		
	
	
}
