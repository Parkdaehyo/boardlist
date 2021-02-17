<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

.grid {
     
        margin: 0 auto;
        text-align: center; /*inline-block의 장점은 폰트의 성질도 포함되고 있어서 text-align으로 정렬을 쉽게 할수가 있다.*/
        font-size: 0; /*양 옆쪽, 밑에 4px정도 여백을 없에주는 방법 하지만 크로스브라우징 호환성헤서는 문제가 있다.*/
}

.size {

width: 615px

}



            table          { border-collapse: collapse; }
            table, th, td  { border: 1px solid; }
            th, td         { padding: 4px; }
            /* Additional style */
            thead tr       { background-color: #cccccc; }
            td.center      { text-align: center; }
            td.right       { text-align: right; }
         
</style>
 <!--      width: 100%;-->

<body>


<br>

<table align="center" width="800" style="text-align:center">

<tr style="background-color :skyblue">
<td>일반게시판</td>
<td>첨부파일 게시판</td>
<td>다중 첨부파일게시판</td>
<td>댓글 게시판</td>
</tr>
</table>

<br>
<br>
 <!--  form role == 부트스트랩 -->
 <form name="articleForm" method="post"  action="${contextPath}/board/write.do">
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
 						
						<tr>
							<td scope="row"  align="center">제목</td>
							<td><input class="size" type="text" id="i_title" name="title" value="${article.title}" /></td>
						</tr>
						<tr>
						    <td scope="row"  width=30 align="center">작성자</td>
							<td><input class="size" ype="text" id="i_title" name="writer" value="${article.writer}" /></td>						
						</tr>
						<tr>
							<td scope="row" width=30 align="center">내용</td>
							<td colspan="3"><textarea id="i_content" name="content" cols="90" rows="10" >${article.content}</textarea></td>
						</tr>
						
						</tbody>
				
						
</table>

<div style="position: absolute; left: 1000px;">
<!-- <div align="center" style="right: 700px;"> -->
		<input  type="submit" value="저장">
		<a href="<c:url value='/board/write'/>">목록</a>
</div>
</form>

</body>

<script>


//제이쿼리 시작
$(function() {
	
	
	$("#list-btn").click(function() {
	
		console.log("목록 버튼이 클릭됨");
		location.href='/board/list?page=${p.page}' 
				+ '&countPerPage=${p.countPerPage}';		
		
	});
	
});

</script>


</html>