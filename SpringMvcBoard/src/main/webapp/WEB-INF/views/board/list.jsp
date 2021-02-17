<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 포맷팅 관련 태그라이브러리(JSTL/fmt) --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<%
  request.setCharacterEncoding("UTF-8");
%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>

.grid {
        width: 500px;
        margin: 0 auto;
        text-align: center; /*inline-block의 장점은 폰트의 성질도 포함되고 있어서 text-align으로 정렬을 쉽게 할수가 있다.*/
        font-size: 0; /*양 옆쪽, 밑에 4px정도 여백을 없에주는 방법 하지만 크로스브라우징 호환성헤서는 문제가 있다.*/
}
    
    
.grid_item {
   
        height: 100px;
        display: inline-block; /*이부분에 성질을 inline-block로 바꿔줘서 가로배치를 해줬다.*/
        vertical-align: top; /*밑에 4px 여백을 없에는것*/
        text-align: center;
}

</style>


<!-- width: 100px; -->    

<body>





<br>




<table align="center">

<tr style="background-color :skyblue">
<td>일반게시판</td>
<td>첨부파일 게시판</td>
<td>다중 첨부파일게시판</td>
<td>댓글 게시판</td>
</tr>
</table>

<br>
<br>


<table align="center">
<tr style="background-color :skyblue">
<td>번호</td>
<td>제목</td>
<td>작성자</td>
<td>조회수</td>
<td>등록일자</td>
</tr>


<c:forEach var="b" items="${articles}">


<tr>
<td>${b.boardNo}</td>
<td>

<a href="<c:url value='/board/content/${b.boardNo}${param.page == null ? pc.makeURI(1) : pc.makeURI(param.page)}' />">

${b.title}
</a>
</td>
<td>${b.writer}</td>
<td>${b.viewCnt}</td>
<td><fmt:formatDate value="${b.regDate}"
pattern="yyyy-MM-dd"/></td>
</tr>
</c:forEach>

</table>

<p>

<div align="center">

		<c:forEach var="pageNum" begin="${pc.beginPage}" end="${pc.endPage}">
						<!-- 1이 시작값 end가 끝값 -->
						<li class="grid_item"><a
							href="<c:url value='/board/list${pc.makeURI(pageNum)}'/>"
							class="${(pc.paging.page == pageNum) ? 'page-active' : ''}" 
							>${pageNum}</a>
						</li>
					</c:forEach>
				<a href="<c:url value='/board/write'/>">등록</a> <!--  p태그를 하면 줄개행이 된다. -->
			
</div>








</body>
</html>