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
	
	
	
	
	/*
	 * @RequestBody: @RequestMapping에 의해서 POST 방식으로  전송된 HTTP 요청 데이터를 String 타입의 body 파라미터로 전달된다.(수신)
	 * 그래서 해당 메서드의 리턴값을 HTTP 응답 데이터로 사용한다?
	 * 
	 */
	
	
	@PostMapping("/write2") 
	@ResponseBody 
	public ResponseEntity write2(BoardVO_two article, RedirectAttributes ra, SearchVO searchvo,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception { 
		

		
		
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		//반복문
		Enumeration enu=multipartRequest.getParameterNames(); // hideen 객체로 인해서 counterPage와 page=1이 넘어온다.
		while(enu.hasMoreElements()){ 
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name,value);
		}
		
		String imageFileName= upload(multipartRequest); // 업로드를 타고 돌아오면 imageFileName에 실질적으로 업로드가 될 준비를 맞췄다는 이야기다.
		articleMap.put("imageFileName", imageFileName);
		
		System.out.println("imageFileName" + imageFileName);
		System.out.println("등록시 넘어오는 imageFileName" + imageFileName);
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders(); // 클라이언트와 서버가 Http 요청과 응답의 추가적인 정보를 건네준다. (한마디로 말하면 인터넷 통신규약을 이용한 요청과 응답을 제어할 수 있는 클래스)
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			int boardNo = service.addNewArticle(articleMap); //아까 엉뚱한 폴더에 저장이 된 이유는 boardNo를 가져오는 테이블이 다른 테이블이라서..
			if(imageFileName!=null && imageFileName.length()!=0) {
				
				File srcFile = new 
				File(ARTICLE_IMAGE_REPO+ "\\" + "temp"+ "\\" + imageFileName); //파일 객체 생성.
				
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+boardNo); //파일 객체 생성2
						 //Moves a file to a directory.
				
				//TEMP폴더에 있던 훈이가 9번 폴더로 이동되었다.
				//srcFile -> desDir 경로로 이동. 
				FileUtils.moveFileToDirectory(srcFile, destDir,true); // FileUtils: 일반적인 파일 처리 관련 기능 클래스. 
			}

			message = "<script>"; 
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/list2'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    
		}catch(Exception e) { //catch문일 경우
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete(); // temp폴더 경로를 아예 삭제시킨다.
			
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
		
		System.out.println("수정 요청 진입시 vo" + vo);
	
		System.out.println("Result Data: " + vo);
		model.addAttribute("article" , vo);
		//model.addAttribute("pc",pc);
		
		int b = 2;
		model.addAttribute("A", b);
		
		
		
		return "board/write2";
	}
	
	//첨부파일 수정
	@PostMapping("/modify2")
	@ResponseBody
	public ResponseEntity modify2(BoardVO_two article, RedirectAttributes ra, SearchVO searchvo
			, MultipartHttpServletRequest multipartRequest,  
		    HttpServletResponse response) throws Exception{ 
		
		  multipartRequest.setCharacterEncoding("utf-8");
		  Map<String,Object> articleMap = new HashMap<String, Object>();
		  
		  //파라미터 가져온다.
		  Enumeration enu=multipartRequest.getParameterNames(); //getParameterNames: getParameter만 단일 name값들을 반환한다면 이 메서드는 폼에서 전송된 모든 name값을 받을 수 있다.
		  
		  System.out.println("enu 값:" + enu);
		  
			while(enu.hasMoreElements()){ //hasmoreElements: 읽어올 요소가 남아있는지 확인. 있으면 true, 없으면 false. Iterator의 hasNext()와 같음
				String name=(String)enu.nextElement(); // Enumeration 내의 다음 요소를 반환한다. 하나하나씩 name에담는다
				String value=multipartRequest.getParameter(name); //그리고 그 파라미터들을 value에 하나씩 넣는다.
				articleMap.put(name,value); //map데 value값에 저장한다.
			}
		  
			String imageFileName= upload(multipartRequest);
			

			/*
			 * if(imageFileName == null) { String imageFileName1= (String)
			 * articleMap.get("imageFileName"); System.out.println("imageFileName1" +
			 * imageFileName1); articleMap.put("imageFileName", imageFileName1); } else {
			 * 
			 * articleMap.put("imageFileName", imageFileName); }
			 */
			
			
			
			articleMap.put("imageFileName", imageFileName);
		  
		String boardNo = (String)articleMap.get("boardNo");
		String pageNo = (String)articleMap.get("page");
		
		System.out.println("modfify2의 boardNo");
		String message;
		ResponseEntity resEnt = null;
		
		//A data structure representing HTTP request or response headers
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			
			if(imageFileName == null ||  imageFileName == "") {
				service.update2(articleMap);
			} else {
				
			   service.updateArticle(articleMap);
			}
			
			
		 
			if(imageFileName != null && imageFileName.length() != 0) {
				  File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + boardNo);
				
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				//originalFileName == ${article.imageFileName} -- 기존에 저장되있던 이미지 파일을 가져온다.
				String originalFileName = (String)articleMap.get("originalFileName");
				//이 oldFile 경로에 접근해서 기존의 이미지 파일을
				File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + boardNo + "\\" + originalFileName);
				// 삭제 시킨다.
				oldFile.delete();			  
			} 
		
		message = "<script>";
		message += " alert('수정 되었습니다..');";
		//message += " location.href='"+multipartRequest.getContextPath()+"/board/list2?page="+pageNo+"';";
		message += " location.href='"+multipartRequest.getContextPath()+"/board/list2?page="+pageNo+"';";
		message +="</script>";	
		  resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch(Exception e) {
			
			e.printStackTrace();
			
			  File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			  message = "<script>";
			  message += " alert('오류');";
			  message += " location.href='"+multipartRequest.getContextPath()+"/board/list2?boardNo="+boardNo+"';";
			  message +=" </script>";
		      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		
		return resEnt;
	
			
		//return "";
		//System.out.println("parameter(board/): " + article);
		//service.update2(article);
		//ra.addFlashAttribute("msg" , "modSuccess");
		//return "redirect:/board/list/?page=" + searchvo.getPage();
	}
	
	
	//한개 이미지 업로드하기
		private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
			
			String imageFileName= null;
			
			//Iterator(반복자): 컬렉션을 보관하고 있는 자료들을 순차적으로 접근하면서 처리할때 사용하는 클래스.
			
			/*
			 * 
			 * 컬렉션 내에 보관한 모든 내용을 출력하는 등의 작업을 먼저 하길 원한다면 Iterator를 사용하는 것은 좋은 선택입니다. 
			 * 
			 * List로 저장한 객체나 set으로 저장한 객체를
			 * Iterator 클래스로 모두 받을 수 있다. [받아오는 방법이 일정하다
			 * 
			 */
			
											
			Iterator<String> fileNames = multipartRequest.getFileNames(); //jsp에서 input="file"를 가져오는것같음.
			
			while(fileNames.hasNext()){ //Iterator에 내부요소가 있느냐? true면 while문 돌아라. Returns true if the iteration has more elements., (In other words, returns true if next would return an element rather than throwing an exception.)
				String fileName = fileNames.next(); //next로 각각의 Iterator 요소를 하나씩 가져온다  . //Returns the next element in the iteration.
				
				//A representation of an uploaded file received in a multipart request. 
				//MultipartFile: 다중요청에서 업로드된 파일 , getFile: 업로드된 파일들을 리턴한다. 존재하지 않으면 null을 리턴한다.
				//이러한 multipartRequest.getFIle()을 받은 클래스가 MultipartFile이다.
				MultipartFile mFile = multipartRequest.getFile(fileName); //Return the contents plus description of an uploaded file in this request,or null if it does not exist.
				
															//fileName: 실제로 getFileNames()에서 가져온 실제 파일 객체들
				
				imageFileName=mFile.getOriginalFilename(); //파일 이름을 string으로 반환한다.
				//An abstract representation of file and directory pathnames.
				
			    //추상 경로 
				
				/*
				 * getParentFile()은 부모폴더(ARTICLE_IMAGE_REPO)를 리턴해서 만들어주는데
				 * temp폴더는 안만들어주기때문에그래서 내가 만들어야 됬구나........ 아.......
				 * 
				 * file 객체 생성 시 ARTICLE_IMAGE_REPO￦temp￦..으로 생성 되었으니 부모 디렉터리는 temp의 상위 디렉터리 경로인 ARTICLE_IMAGE_REPO가 됩니다.
				 * 
				 * 
				 */
								//ARTICLE_IMAGE_REPO는 부모 폴더 //temp은 자식폴더
				File file = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\" + fileName); //파일 경로의 객체를 생성한다.
				
				if(mFile.getSize()!=0){ //File Null Check
					if(!file.exists()){ // 434번에서 생성한 경로에 파일이 존재하지 않을 경우
							//getParentFile(): 현재 파일, 디렉토리의 부모를 파일객체로 리턴한다. (부모 폴더를 만든다)
						
						//file 객체에 지정된 경로에 해당하는 디렉토리 구조가 없을때, 디렉토리를 생성. mkdir은 make directory의 약자
						//내 경우에는 이미 만들어져있어서 다시 만들어지지 않았다.
						
						//getParentFile()이 File file = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\" + fileName); 이 경로를 하나의 부모로서 인식을 한다.
						file.getParentFile().mkdirs();  //mkdir: Creates the directory named by this abstract pathname, including anynecessary but nonexistent parent directories.  경로에 해당하는 디렉토리들을 생성
						
						// Transfer the received file to the given destination file. 
						// transferTO: 수신된 파일을 지정된 대상 파일로 이동시킨다.
						
						//이 시점에서 이미 temp 폴더에 파일명이 저장이된다.
						mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+imageFileName)); ///파일이 없으면 temp 폴더에 이미지를 저장시킴.
					}
				}
				
			}
			return imageFileName;
		}
	
		@PostMapping("/delete2")
		public String remove2(Integer boardNo, PageVO paging, RedirectAttributes ra) {
			
			//System.out.println("URL: /board/delete => POST");
			//System.out.println("parameter(글번호): " + boardNo);
			service.delete2(boardNo);
			ra.addFlashAttribute("msg", "delSuccess") 
			  .addAttribute("page" , paging.getPage()) 
			  .addAttribute("countPerPage" , paging.getCountPerPage());
			
			return "redirect:/board/list2";
		
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
