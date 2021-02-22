package com.spring.mvc.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FileDownloadController2 {				//"C:+File.separator+board+File.separator+article_image";
	private static final String ARTICLE_IMAGE_REPO ="C:+File.separator+board+File.separator+article_image";
	@RequestMapping("/download2.do")
	protected void download(@RequestParam("imageFileName1") String imageFileName1, //이미지 파일 이름을 바로 설정한다.
							@RequestParam("imageFileName2") String imageFileName2,
							@RequestParam("imageFileName3") String imageFileName3,
							@RequestParam("boardNo") String boardNo,
			                 HttpServletResponse response)throws Exception {
		
		
		
		
		OutputStream out = response.getOutputStream(); //출력할 Output stream을 얻는다.  
	
		
		/*
		  WARN : org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver -
		  Resolved [org.springframework.web.bind.MissingServletRequestParameterException:
		  Required String parameter 'articleNO' is not present]
		 */
	
		//이 경로를 읽어야만 이미지 파일이 출력된다.				
		String downFile1 = ARTICLE_IMAGE_REPO + File.separator +boardNo+ File.separator+ imageFileName1; 
		String downFile2 = ARTICLE_IMAGE_REPO + File.separator +boardNo+ File.separator+ imageFileName2; 
		String downFile3 = ARTICLE_IMAGE_REPO + File.separator +boardNo+ File.separator+ imageFileName3; 
		//글 번호와 파일 이름으로 다운로드 할 파일 경로를 설정한다.
		File file1 = new File(downFile1); //다운로드 할 파일 객체를 생성한다.
		File file2 = new File(downFile2);
		File file3 = new File(downFile3);
		
		response.setHeader("Cache-Control", "no-cache");
		
		//이미지 파일을 내려 받는데 필요한 response에 헤더정보를 설정합니다.
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName1);
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName2);
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName3);
		//파일을 받을 수 있도록 해주는 메서드?
		
		FileInputStream in1 = new FileInputStream(file1);
		FileInputStream in2 = new FileInputStream(file2);
		FileInputStream in3 = new FileInputStream(file3);
		
		
		//버퍼를 이용해 한 번에 8kb씩 전송합니다.
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in1.read(buffer); // in.read() 파일 이미지를 8kb씩 읽어드린다.
			if (count == -1) 
				break;
			out.write(buffer, 0, count); //out.write: 브라우저로 출력한다.
		}
		in1.close();
		
		out.close();
		
		byte[] buffer2 = new byte[1024 * 8];
		while (true) {
			int count = in2.read(buffer2); // in.read() 파일 이미지를 8kb씩 읽어드린다.
			if (count == -1) 
				break;
			out.write(buffer, 0, count); //out.write: 브라우저로 출력한다.
		}
		in2.close();
		
		out.close();
		
		byte[] buffer3 = new byte[1024 * 8];
		while (true) {
			int count = in3.read(buffer3); // in.read() 파일 이미지를 8kb씩 읽어드린다.
			if (count == -1) 
				break;
			out.write(buffer, 0, count); //out.write: 브라우저로 출력한다.
		}
		in3.close();
		out.close();
	}
	
	
	

}
