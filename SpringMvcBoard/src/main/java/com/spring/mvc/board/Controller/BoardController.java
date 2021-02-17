package com.spring.mvc.board.Controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IBoardService;
import com.spring.mvc.commons.PageCreator;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;


@Controller
@RequestMapping("/board")
public class BoardController {

	
	@Inject
	private IBoardService service;
	
	
	@GetMapping("/list")
	public String list(SearchVO search, Model model) {
		
		//List<BoardVO> list = service.getArticleList();
		
		//System.out.println("URL: /board/list GET -> result: " + list.size());


		PageCreator pc = new PageCreator(); //PageVO의 객체와, 페이징 알고리즘을 실행하는 로직의 객체를 생성
		pc.setPaging(search); 
		
		
		List<BoardVO> list = service.getArticleList(search);
		pc.setArticleTotalCount(service.countArticles(search));
		
		model.addAttribute("articles", list);
		model.addAttribute("pc" , pc);
	
		return "board/list";
		
		
	}
	//@PathVariable를 사용하면 URL에서 파라미터를 보내서 사용 할 수 있다.
	//url path 로 들어오는 값을 해석 그대로 변수로 사용하겠다는 의미이다.
	@GetMapping("/content/{boardNo}") //20
	public String content(@PathVariable Integer boardNo, HttpSession session, Model model
			,@ModelAttribute("p") SearchVO paging) {
		System.out.println("URL: /board/content => GET"); 
		System.out.println("parameter(글번호): " + boardNo); //20
		BoardVO vo = service.getArticle(boardNo); 
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo); 
		return "board/content";
	}
	
	
	@GetMapping("/modify")
	public String modify(Integer boardNo, Model model
			, @ModelAttribute("p") PageVO paging) {

		System.out.println("URL: /board/content => GET"); 
		System.out.println("parameter(글번호): " + boardNo);
		
		BoardVO vo = service.getArticle(boardNo);
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo); 
		
		return "board/write";
	}
	
	
	
	
	@GetMapping("/write")
	public void write() { //void로 처리하면 알아서 board/write.jsp가 열린다.
		System.out.println("URL: /board/write 글쓰기 Get요청!");
	}
	
	@PostMapping("/write") 
	public String write(BoardVO article, RedirectAttributes ra) { 
		

		System.out.println("Controller parameter: " + article);
	
	
	    service.insert(article);
		
		ra.addFlashAttribute("msg" , "regSuccess");
		
		return "redirect:/board/list";
	}
	
	
	
	@PostMapping("/delete")
	public String remove(Integer boardNo, PageVO paging, RedirectAttributes ra) {
		
		System.out.println("URL: /board/delete => POST");
		System.out.println("parameter(글번호): " + boardNo);
		service.delete(boardNo);
		ra.addFlashAttribute("msg", "delSuccess") 
		  .addAttribute("page" , paging.getPage()) 
		  .addAttribute("countPerPage" , paging.getCountPerPage());
		
		return "redirect:/board/list";
	
	}
	
	
//	//게시글 DB등록 요청 --> write.jsp에서 name값이 전달 된다. post요청
//	@PostMapping("/write.do")
//	public String write(@RequestParam("num") int num, BoardVO article, RedirectAttributes ra) {
//		
//		
//		switch(num) {
//		
//		case 1: 
//		
//		//글쓰기
//		service.insert(article);
//		
//		case 2:
//		
//		//수정
//	    service.update(article);
//	
//		
//		default:
//				
//	    break;
//		}
//		
//		
//		System.out.println("/board/write -> Post");
//		System.out.println("post article이 넘어오는가?" + article);
//		service.insert(article);
//		ra.addFlashAttribute("msg","regSuccess"); // 이 구문을 아래
//		
//		return "redirect:/board/list"; //여기로 보낸다.
//	}
	

	
	
	
	
	
	
	
	
	
}
