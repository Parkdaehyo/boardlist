<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 포맷팅 관련 태그라이브러리(JSTL/fmt) --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


  		<script
 		src="https://code.jquery.com/jquery-3.5.0.min.js" 
		integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ="
 		crossorigin="anonymous"></script>

</head>

<style>


.save {

background-color:red

}

.to-list {

background-color:#969696

}


.grid {
     
        margin: 0 auto;
        text-align: center; /*inline-block의 장점은 폰트의 성질도 포함되고 있어서 text-align으로 정렬을 쉽게 할수가 있다.*/
        font-size: 0; /*양 옆쪽, 밑에 4px정도 여백을 없에주는 방법 하지만 크로스브라우징 호환성헤서는 문제가 있다.*/
}

.size {

width: 615px

}

.size2 {

width: 150px

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


.button2 {

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








</style>
 <!--      width: 100%;-->

<body>


<br>

<table align="center" width="800" style="text-align:center; border-color: white;">

<tr style="background-color :#1E82FF">
<td ><a href="<c:url value='/board/list/'/>" style="color:white;">일반게시판</a></td>
<td ><a href="<c:url value='/board/list/'/>" style="color:white;">첨부파일 게시판</a></td>
<td ><a href="<c:url value='/board/list/'/>" style="color:white;">다중 첨부파일 게시판</a></td>
<td ><a href="<c:url value='/board/list/'/>" style="color:white;">댓글 게시판</a></td>
</tr>
</table>

<br>
<br>







<!-- 

여백 지정
margin 5px 7px 3px 0px; (위, 오른쪽, 아래, 왼쪽 순)


 -->



 <!--  form role == 부트스트랩 -->
 <form id= "formObj" name="articleForm" method="post"  action="${contextPath}/board/write">
<table align="center">
					<colgroup>
						<col style="width:12%;" /><col style="width:auto;" /><col style="width:12%;" /><col style="width:38%;" />
					</colgroup>
						<tbody>
						
				<%-- 	   <c:forEach var="image" items="${imageFileList}"> 
						 <input type="hidden" name="imageFileNO" value="${image.imageFileNO}" />
						 <input type="hidden" name="imageFileNO1" value="${image.imageFileNO}" />
						 <input type="hidden" name="imageFileNO2" value="${image.imageFileNO}" />
						 	
						 	 	 	 	 	 	 
						</c:forEach>
						 --%>
				
 						 <input id="boardno" type="hidden" name="boardNo" value="${article.boardNo}" />
 						 
 						 
          				<input type="hidden" name="page" value="${p.page}">
          				<input type="hidden" name="countPerPage" value="${p.countPerPage}">
 						
 						
						<tr>
							<td scope="row"  align="center">제목</td>
							<td><input class="size" type="text" id="title" name="title" value="${article.title}" /></td>
						</tr>
						<tr>
						    <td scope="row"  width=30 align="center">작성자</td>
							<td><input class="size2" type="text" id="writer" name="writer" value="${article.writer}" /></td>						
						</tr>
						<tr>
							<td scope="row" width=30 align="center">내용</td>
							<td colspan="3"><textarea id="content" name="content" cols="90" rows="10" >${article.content}</textarea></td>
						</tr>
						
	<input type="hidden" id="A" value="${A}">
						</tbody>
				
						
</table>

						 

<div style="position: absolute; left: 1000px;">
<!-- <div align="center" style="right: 700px;"> -->
		<button type="button" id="save" class="button" style="color:white;">저장</button>
	<!-- 	<input id="save" class="save" style="color:white;" type="submit" value="저장"> -->
		<input class="button2" type="button" value="목록" style="color:white;" id="list-btn2">
</div>
</form>

</body>

<script>


//제이쿼리 시작
$(function() {
	
	
	$("#list-btn2").click(function() {
	
		console.log("목록 버튼이 클릭됨");
		location.href='/board/list';		
		
	});
	
	
	$(document).ready(function() {
		
	$("#save").click(function() {
	
		var blank_pattern = /^\s+|\s+$/g;
		
		
		var title = $("#title").val();
		var content = $("#content").val();
		var writer= $("#writer").val();
		var A = $("#A").val();
		
		if(title.replace(blank_pattern, "") == "") {
			
			alert("제목을 입력해주시겠습니까?");
			document.articleForm.title.focus();	
			return;
		}
		
		if(writer.replace(blank_pattern, "") == "") {
			
			alert("작성자를 입력해주시겠습니까?");
			document.articleForm.writer.focus();	
			return;
		}	
		
		if(content.replace(blank_pattern, "") == "") {
			
			alert("내용을 입력해주시겠습니까?");
			document.articleForm.content.focus();	
			return;
		}
	
			
		if(A == 1) {
			
			
			document.articleForm.action = "${contextPath}/board/write?boardNo=1"
			document.articleForm.submit();
			
		} else {
			
			

			const formElement = $("#formObj"); 
			
			formElement.attr("action" , "/board/modify");//attr(속성 , 변경값 ) 태그의 내부 속성을 변경 , action 속성을 /board/modify로 변경
			formElement.attr("method", "post"); 
			formElement.submit();
			
			
			
		}
		
		
		
		
		
		
		
	
	});
		
		
		
		
		
		
		
		
		console.log("저장 버튼이 클릭됨");
			
		
		
		
		
	});
	
	
	
	
	
	
	
});

</script>


</html>