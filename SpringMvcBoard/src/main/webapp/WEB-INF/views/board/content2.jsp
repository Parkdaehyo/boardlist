<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <!--  이 구문을 넣으면 jQuery를 쓸 수 있다. -->
        <script
 		src="https://code.jquery.com/jquery-3.5.0.min.js" 
		integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ="
 		crossorigin="anonymous"></script>


</head>


<script>


function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
      
           $('#preview').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}  

</script>



<style>

.grid {
     
        margin: 0 auto;
        text-align: center; /*inline-block의 장점은 폰트의 성질도 포함되고 있어서 text-align으로 정렬을 쉽게 할수가 있다.*/
        font-size: 0; /*양 옆쪽, 밑에 4px정도 여백을 없에주는 방법 하지만 크로스브라우징 호환성헤서는 문제가 있다.*/
}




   		
            table, th, td  { border: 1px solid; }
            th, td         { padding: 4px; }
            /* Additional style */
            thead tr       { background-color: #cccccc; }
            td.center      { text-align: center; }
            td.right       { text-align: right; }
            
            
.button {

  background-color: red;

  border: none;

  color: white;

  padding: 5px 10px;

  text-align: center;

  text-decoration: none;

  display: inline-block;

  font-size: 12px;

  margin: 4px 2px;

  cursor: pointer;
  
  WIDTH: 50pt;

}

.update-button {

  background-color: #1E96FF;

  border: none;

  color: white;

  padding: 5px 10px;

  text-align: center;

  text-decoration: none;

  display: inline-block;

  font-size: 12px;

  margin: 4px 2px;

  cursor: pointer;
  
  WIDTH: 50pt;

}

.to-list {

  background-color: #969696;

  border: none;

  color: white;

  padding: 5px 10px;

  text-align: center;

  text-decoration: none;

  display: inline-block;

  font-size: 12px;

  margin: 4px 2px;

  cursor: pointer;
  
  WIDTH: 50pt;

}



.size2 {

width: 150px

}


 
</style>
 <!--      width: 100%;-->
 

<body>

<br>

<table align="center" width="800" style="text-align:center; border-color: white;    border-style: dashed;">

<tr style="background-color :#1E82FF">
<td ><a href="<c:url value='/board/list/'/>" style="color:white;">일반게시판</a></td>
<td ><a href="<c:url value='/board/list2/'/>" style="color:white;">첨부파일 게시판</a></td>
<td ><a href="<c:url value='/board/list3/'/>" style="color:white;">다중 첨부파일 게시판</a></td>
<td ><a href="<c:url value='/board/list4/'/>" style="color:white;">댓글 게시판</a></td>
</tr>
</table>


	<%-- <colgroup>
						<col style="width:12%;" /><col style="width:auto;" /><col style="width:12%;" /><col style="width:38%;" />
					</colgroup> --%>

					<table align="center">
				
						<tbody>
						
				<%-- 	   <c:forEach var="image" items="${imageFileList}"> 
						 <input type="hidden" name="imageFileNO" value="${image.imageFileNO}" />
						 <input type="hidden" name="imageFileNO1" value="${image.imageFileNO}" />
						 <input type="hidden" name="imageFileNO2" value="${image.imageFileNO}" />
						 	
						 	 	 	 	 	 	 
						</c:forEach>
						 --%>
 						
						<tr>
							<td scope="row"  align="center">제목</td>
							<td class="size">${article.title}</td>
					<%-- 		<td><input style="border:none" class="size" type="text" id="i_title" name="title" value="${article.title}" readonly/></td> --%>
						</tr>
						<tr>
						    <td scope="row"  width=30 align="center">작성자</td>
						    <td id="check">${article.writer}</td>
							<%-- <td><input style="border:none" class="size2" type="text" id="i_title" name="writer" value="${article.writer}" readonly/></td>	 --%>					
						</tr>
						<tr>
							<td scope="row" width=30 align="center">내용</td>
							<td colspan="3"><textarea id="i_content" name="content" cols="90" rows="10" readonly>${article.content}</textarea></td>
						</tr>
						
						
						 <c:choose> 
		
	  <c:when test="${not empty article.imageFileName && article.imageFileName!='null' }">
	  
	   	<tr>
		    <td width="150" align="center" bgcolor=""  rowspan="2">
		      첨부파일
		   </td>
		   <td>
		     <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
		     
		     <!-- 
		     src 특성은 필수이며, 포함하고자 하는 이미지로의 경로를 지정합니다.
		     alt : 이미지의 텍스트 설명이며, 필수는 아니지만 스크린 리더가 alt의 값을 읽어 이미지를 설명하므로 접근성 차원에서 매우 유용합니다.
		     
		      -->
		    <img src="${contextPath}/download.do?boardNo=${article.boardNo}&imageFileName=${article.imageFileName}" id="preview" width="200" /><br>
	<%-- 	   <span onclick="download()">${article.imageFileName}</span> --%>
	 		 <span id="imagedown">이미지 다운: ${article.imageFileName}</span> 
		   </td>   
		  </tr>  
		
	
		 <%--     <img alt="${article.imageFileName}" onclick="javascript:location.href='http://www.naver.com';" /> --%>
		<%--      <img alt="${article.imageFileName}" onclick="download();"> --%>
		    
		    
	
		 </c:when>
		 <c:otherwise>
		    <tr  id="tr_file_upload" >
				<!--     <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
				      이미지
				    </td> -->
				    <td>
				      <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
				    </td>
			  
				    <td>
				       <img id="preview"  /><br>
				       <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   />
				    </td>
				    
			  </tr>
		 </c:otherwise>
	 </c:choose>
						
			
			
						
						</tbody>
</table>



 <form id="formObj" action="<c:url value='/board/delete2'/>" method="post">  

   		  <input type="hidden" name="boardNo" value="${article.boardNo}">
          <input type="hidden" name="page" value="${p.page}">
          <input type="hidden" name="countPerPage" value="${p.countPerPage}">
          <input type="hidden" name="imageFileName" value="${article.imageFileName}">



<div style="position: absolute; left: 1000px;">
    <input id="modBtn" class="update-button" type="button" value="수정" class="btn" id="">
    <input type="submit" class="button" value="삭제" id="" onclick="return confirm('정말로 삭제하시겠습니까?')">
    <input type="button" class="to-list" value="목록" class="btn" id="list-btn">

</div>

</form>

<!--  컨트롤러의 패키지의 위치는 상관없이 매핑명이 download.do이므로 다운로드가 가능합니다. 패키지의 위치는 상관없습니다 -->

<c:if test="${not empty article.imageFileName}">
<div style="position: absolute; left: 850px;">

<form id="formimagedown" method="post" action="${contextPath}/download.do?boardNo=${article.boardNo}&imageFileName=${article.imageFileName}">



<input type="submit"  style="background-color:transparent;  border:0px transparent solid; display:none"/>	

</c:if>	

</form>
</div>
</body>

<script>


//제이쿼리 안에다가 자바스크립트 함수 쓰니까 안됬지.
function download() {
	
	
}


<%-- 		<input type="hidden" name="imageFileName" value="${article.imageFileName}" /> <br> --%>
<%-- <input type="hidden" name="boardNo" value="${article.boardNo}" /> <br> --%>

//제이쿼리 시작
$(function() {
	
	
	$("#list-btn").click(function() {
	
		console.log("목록 버튼이 클릭됨");
		location.href='/board/list2?page=${p.page}' 
				+ '&countPerPage=${p.countPerPage}';		
		
	});
	
	
	const formElement = $("#formObj"); 
	
	var modifyBtn = $("#modBtn");
	modifyBtn.click(function() { //클릭 했을때 생성되는 이벤트 처리
		console.log("수정 버튼이 클릭됨!");
		formElement.attr("action" , "/board/modify2");//attr(속성 , 변경값 ) 태그의 내부 속성을 변경 , action 속성을 /board/modify로 변경
		formElement.attr("method", "get"); 
		formElement.submit();
	});
	

	const formimagedown = $("#formimagedown");
	
	$("#imagedown").click(function() {
		$("formimagedown").attr({
			
			"action" : "${contextPath}/download.do?boardNo=${article.boardNo}&imageFileName=${article.imageFileName}" 
					
		});
		
		formimagedown.submit();		
		
		
	});
	
	
	
	
});

</script>





</html>