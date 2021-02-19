package com.spring.mvc.board.Controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.model.BoardVO_two;
import com.spring.mvc.board.service.IBoardService;
import com.spring.mvc.commons.PageCreator;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;


@Controller
@RequestMapping("/board")
public class BoardController {
	private static final String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";

	
	@Inject
	private IBoardService service;
	
	
	@GetMapping("/list")
	public String list(SearchVO search, Model model, @ModelAttribute("p") SearchVO paging) { //list.jsp에서 페이징 정보를 가져와서 p로 뿌려준다.
		
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
			,@ModelAttribute("p") SearchVO paging, HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("URL: /board/content => GET"); 
		//System.out.println("parameter(글번호): " + boardNo); //20
		
		
		String boardno2 =request.getParameter("boardNo");
		
		//System.out.println("boardno2가 뭔데?" + boardno2);
		ModelAndView view = new ModelAndView();
		
//		int limitTime = 60;
//		
//		System.out.println("쿠키 생성중");
//		
//		Cookie viewcookie = new Cookie("viewCookie" , boardno2);// 쿠키생성: 이름: viewCookie, 값: Hello_Cookie
//		viewcookie.setPath("/"); //쿠키의 저장경로를 시작 URL로 설정?
//		viewcookie.setMaxAge(limitTime); //쿠키의 유효시간
//		response.addCookie(viewcookie); //response 객체에 실려서 아용자의 웹브라우저로 저장된다.
		

		//service.kepp
		
		//////////////////////////////////////////////////////////////////////////////////
		
	
		
		BoardVO vo = service.getArticle(boardNo, request, response); 
	
		model.addAttribute("article" , vo);
		return "board/content";

}			
		//////////////////////////////////////////////////////////////////////////////	
		
		
		
	
	
	
	@GetMapping("/modify") //여기까지 글번호가 어떻게 전달되는거지? -->input type hidden으로 boardNo가 보내졌다.
	public String modify(Integer boardNo, Model model
			, @ModelAttribute("p") PageVO paging, HttpServletRequest request, HttpServletResponse response, SearchVO search) {

		//System.out.println("URL: /board/content => GET"); 
		//System.out.println("parameter(글번호): " + boardNo);
		
		//PageCreator pc = new PageCreator(); //PageVO의 객체와, 페이징 알고리즘을 실행하는 로직의 객체를 생성
		//pc.setPaging(search); 
		
		BoardVO vo = service.getArticle(boardNo, request, response);
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo);
		//model.addAttribute("pc",pc);
		
		int b = 2;
		model.addAttribute("A", b);
		
		
		
		return "board/write";
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO article, RedirectAttributes ra, SearchVO searchvo) { 
		
		System.out.println("parameter(board/): " + article);
		service.update(article);
		ra.addFlashAttribute("msg" , "modSuccess");
		return "redirect:/board/list/?page=" + searchvo.getPage();
	}
	
	
	@GetMapping("/write") 
	public void write(@RequestParam("page") int page, Model model, @ModelAttribute("p") SearchVO paging) { //void로 처리하면 알아서 board/write.jsp가 열린다.
		
		//RequestParam으로 /board/write?page=${p.page} 에서 전달된 page 객체를 받는다.
		
		//System.out.println("list.jsp에서 넘어온 page" + page);
		int a = 1;
		model.addAttribute("A", a);
		
		
		
		//System.out.println("URL: /board/write 글쓰기 Get요청!");
	}
	
	@PostMapping("/write") 
	public String write(@RequestParam("boardNo") int boardNo, BoardVO article, RedirectAttributes ra, SearchVO searchvo) { 
		
		//System.out.println("전달된 boardNo" + boardNo);
		
		//System.out.println("Controller parameter: " + article);
	
	
	    service.insert(article);
		
		ra.addFlashAttribute("msg" , "regSuccess");
		
		return "redirect:/board/list";
	}
	
	
	
	@PostMapping("/delete")
	public String remove(Integer boardNo, PageVO paging, RedirectAttributes ra) {
		
		//System.out.println("URL: /board/delete => POST");
		//System.out.println("parameter(글번호): " + boardNo);
		service.delete(boardNo);
		ra.addFlashAttribute("msg", "delSuccess") 
		  .addAttribute("page" , paging.getPage()) 
		  .addAttribute("countPerPage" , paging.getCountPerPage());
		
		return "redirect:/board/list";
	
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//첨부파일 게시판
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////첨부파일 게시글 목록
	@GetMapping("/list2")
	public String list2(SearchVO search, Model model, @ModelAttribute("p") SearchVO paging) { //list.jsp에서 페이징 정보를 가져와서 p로 뿌려준다.
		
		//List<BoardVO> list = service.getArticleList();
		
		//System.out.println("URL: /board/list GET -> result: " + list.size());


		PageCreator pc = new PageCreator(); //PageVO의 객체와, 페이징 알고리즘을 실행하는 로직의 객체를 생성
		pc.setPaging(search); 
		
		
		List<BoardVO_two> list = service.getArticleList2(search);
		pc.setArticleTotalCount(service.countArticles2(search));
		
		model.addAttribute("articles", list);
		model.addAttribute("pc" , pc);
	
		return "board/list2";
		
		
	}
	
	////////////////////////////첨부파일 write Get요청
	@GetMapping("/write2") 
	public void write2(@RequestParam("page") int page, Model model, @ModelAttribute("p") SearchVO paging) { //void로 처리하면 알아서 board/write.jsp가 열린다.
		
		//RequestParam으로 /board/write?page=${p.page} 에서 전달된 page 객체를 받는다.
		
		//System.out.println("list.jsp에서 넘어온 page" + page);
		int a = 1;
		model.addAttribute("A", a);
		
		
		
		//System.out.println("URL: /board/write 글쓰기 Get요청!");
	}
	
	//////////////////////////////첨부파일 write poast요청 //@RequestParam("boardNo") int boardNo
	
	
	
	
	
	
	
	@PostMapping("/write2") 
	@ResponseBody
	public ResponseEntity write2(BoardVO_two article, RedirectAttributes ra, SearchVO searchvo,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception { 
		

		
		
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name,value);
		}
		
		String imageFileName= upload(multipartRequest);

		articleMap.put("imageFileName", imageFileName);
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			int boardNo = service.addNewArticle(articleMap); //아까 엉뚱한 폴더에 저장이 된 이유는 boardNo를 가져오는 테이블이 다른 테이블이라서..
			if(imageFileName!=null && imageFileName.length()!=0) {
				File srcFile = new 
				File(ARTICLE_IMAGE_REPO+ "\\" + "temp"+ "\\" + imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+boardNo);
				FileUtils.moveFileToDirectory(srcFile, destDir,true);
			}

			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/list2'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete();
			
			message = " <script>";
			message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/board/write2'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
			
	
	    //service.insert2(article);
		
		//ra.addFlashAttribute("msg" , "regSuccess");
		
		return resEnt;
	}
	
	
	
	//url path 로 들어오는 값을 해석 그대로 변수로 사용하겠다는 의미이다.
	@GetMapping("/content2/{boardNo}") //20
	public String content2(@PathVariable Integer boardNo, HttpSession session, Model model
			,@ModelAttribute("p") SearchVO paging, HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("URL: /board/content => GET"); 
		//System.out.println("parameter(글번호): " + boardNo); //20
		
		
		String boardno2 =request.getParameter("boardNo");
		
		
		//System.out.println("boardno2가 뭔데?" + boardno2);
		ModelAndView view = new ModelAndView();

	
		//service.kepp
		
		//////////////////////////////////////////////////////////////////////////////////
		
		BoardVO_two vo = service.getArticle2(boardNo, request, response); 
		
		System.out.println("content2의 vo객체" + vo);
	
		model.addAttribute("article" , vo);
		return "board/content2";
	
	}
	
	

	@GetMapping("/modify2") //여기까지 글번호가 어떻게 전달되는거지? -->input type hidden으로 boardNo가 보내졌다.
	public String modify2(Integer boardNo, Model model
			, @ModelAttribute("p") PageVO paging, HttpServletRequest request, HttpServletResponse response, SearchVO search) {

		//System.out.println("URL: /board/content => GET"); 
		//System.out.println("parameter(글번호): " + boardNo);
		
		//PageCreator pc = new PageCreator(); //PageVO의 객체와, 페이징 알고리즘을 실행하는 로직의 객체를 생성
		//pc.setPaging(search); 
		
		BoardVO_two vo = service.getArticle2(boardNo, request, response);
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo);
		//model.addAttribute("pc",pc);
		
		int b = 2;
		model.addAttribute("A", b);
		
		
		
		return "board/write";
	}
	
	
	@PostMapping("/modify2")
	public String modify2(BoardVO_two article, RedirectAttributes ra, SearchVO searchvo) { 
		
		System.out.println("parameter(board/): " + article);
		service.update2(article);
		ra.addFlashAttribute("msg" , "modSuccess");
		return "redirect:/board/list/?page=" + searchvo.getPage();
	}
	
	
	//한개 이미지 업로드하기
		private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
			String imageFileName= null;
			Iterator<String> fileNames = multipartRequest.getFileNames();
			
			while(fileNames.hasNext()){
				String fileName = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(fileName);
				imageFileName=mFile.getOriginalFilename();
				File file = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\" + fileName);
				if(mFile.getSize()!=0){ //File Null Check
					if(!file.exists()){ //경로상에 파일이 존재하지 않을 경우
						file.getParentFile().mkdirs();  //경로에 해당하는 디렉토리들을 생성
						mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+imageFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
					}
				}
				
			}
			return imageFileName;
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
