package com.spring.mvc.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FileDownloadController {				//"C:+File.separator+board+File.separator+article_image";
	private static final String ARTICLE_IMAGE_REPO ="C:+File.separator+board+File.separator+article_image";
	@RequestMapping("/download.do")
	protected void download(@RequestParam("imageFileName") String imageFileName, //이미지 파일 이름을 바로 설정한다.
							@RequestParam("boardNo") String boardNo,
			                 HttpServletResponse response)throws Exception {
		
		OutputStream out = response.getOutputStream(); //출력할 Output stream을 얻는다.  
	
		
		/*
		  WARN : org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver -
		  Resolved [org.springframework.web.bind.MissingServletRequestParameterException:
		  Required String parameter 'articleNO' is not present]
		 */
	
		//이 경로를 읽어야만 이미지 파일이 출력된다.				
		String downFile = ARTICLE_IMAGE_REPO + File.separator +boardNo+ File.separator+ imageFileName; 	//글 번호와 파일 이름으로 다운로드 할 파일 경로를 설정한다.
		File file = new File(downFile); //다운로드 할 파일 객체를 생성한다.

		
		imageFileName = new String(imageFileName.getBytes("UTF-8"), "ISO-8859-1");
		
		response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(imageFileName, "utf-8") + ";");

		
		
		//이미지 파일을 내려 받는데 필요한 response에 헤더정보를 설정합니다.
		
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName); //파일을 받을 수 있도록 해주는 메서드?
		
		

		
		FileInputStream in = new FileInputStream(file);
		
		//버퍼를 이용해 한 번에 8kb씩 전송합니다.
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); // in.read() 파일 이미지를 8kb씩 읽어드린다.
			if (count == -1) 
				break;
			out.write(buffer, 0, count); //out.write: 브라우저로 출력한다.
		}
		in.close();
		out.close();
	}
	
	

}
