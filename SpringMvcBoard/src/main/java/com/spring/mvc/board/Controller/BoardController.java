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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IBoardService;
import com.spring.mvc.commons.PageCreator;
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
	
	@GetMapping("/content/{boardNo}")
	public String content(@PathVariable Integer boardNo, HttpSession session, Model model
			,@ModelAttribute("p") SearchVO paging) {
		System.out.println("URL: /board/content => GET"); 
		System.out.println("parameter(글번호): " + boardNo);
		BoardVO vo = service.getArticle(boardNo); 
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo); 
		return "board/content";
	}
	
	
	
	
	
	@GetMapping("/write")
	public void write() {
		System.out.println("URL: /board/write 글쓰기 Get요청!");
	}
	
	
	//게시글 DB등록 요청 --> write.jsp에서 name값이 전달 된다. post요청
	@PostMapping("/write")
	public String write(BoardVO article, RedirectAttributes ra) {
		
		System.out.println("/board/write -> Post");
		System.out.println("post article이 넘어오는가?" + article);
		service.insert(article);
		ra.addFlashAttribute("msg","regSuccess"); // 이 구문을 아래
		
		return "redirect:/board/list"; //여기로 보낸다.
	}

	
	
	
	
	
	
	
	
	
}
