
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 포맷팅 관련 태그라이브러리(JSTL/fmt) --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<%
  request.setCharacterEncoding("UTF-8");
%>      
    

<jsp:include page="header.jsp" />
<!--  테이블에다가 속성값을 주면 tr td는 자동으로 적용된다. -->
<table style="border-color: white;" align="center" width="800" style="text-align:center" >
<tr style="background-color :#1E82FF">

<td style="color:white;">번호</td>
<td style="color:white;">제목</td>
<td style="color:white;">첨부파일</td>
<td style="color:white;">작성자</td>
<td style="color:white;">조회수</td>
<td style="color:white;">등록일자</td>
</tr>
</div>


<c:forEach var="b" items="${articles}">
<c:set var="isFileExisted" value="false"></c:set>
<c:forEach var="image" items="${imageFileList}">
<c:if test="${b.boardNo == image.boardNo and not empty image.imageFileName}">
<c:set var="isFileExisted" value="true"></c:set>
</c:if>
</c:forEach>

<tr>
<td>${b.boardNo}</td>
<td width="300" style="text-align:left">
												<!--  후에 pc.makeURI에 대해서 상세히 알아볼것. -->
<a href="<c:url value='/board/content3/${b.boardNo}${param.page == null ? pc.makeURI(1) : pc.makeURI(param.page)}' />">

${b.title}
</a>
<td>
<c:if test="${isFileExisted eq true}">
<img src="/resources/images.jpg" width="20" />
</c:if>
</td>
</td>
<td>${b.writer}</td>
<td style="text-align:center">${b.viewCnt}</td>
<td><fmt:formatDate value="${b.regDate}"
pattern="yyyy-MM-dd"/></td>
</tr>
</c:forEach>

</table>

<p>



<div align="center">


		<c:if test="${pc.prev}"> <!--  이전버튼이 true일때만 등장. -->
					<a href="<c:url value='/board/list3${pc.makeURI(pc.beginPage - 1)}'/>" 
						>이전</a>
					
					</c:if>
				

		<c:forEach var="pageNum" begin="${pc.beginPage}" end="${pc.endPage}">
						<!-- 1이 시작값 end가 끝값 -->
						<li class="grid_item"><a
							href="<c:url value='/board/list3${pc.makeURI(pageNum)}'/>"
							class="${(pc.paging.page == pageNum) ? 'page-active' : ''}" 
							>${pageNum}</a>
						</li>
					</c:forEach>
					<c:if test="${pc.next}"> <!-- 이것도, next가 true일때만 등장. -->
					<a href="<c:url value='/board/list3${pc.makeURI(pc.endPage + 1)}'/>"
						>다음</a>
				
					</c:if>	
				<a href="<c:url value='/board/write_and_modify3?page=${p.page}&boardNo=${b.boardNo}'/>">등록</a> <!--  p태그를 하면 줄개행이 된다. -->
			
	
		<!--  다음 버튼 -->
				
			
					</div>


<script>

const result = "${msg}";

if(result === "regSuccess") {
alert("게시글 등록이 완료 되었습니다.");
} else if(result === "delSuccess") {
	alert("게시글 삭제가 완료 되었습니다.");	
} else if(result === "regScuccess") {
	alert("수정 완료 됬습니다.");
}




</script>






</body>
</html>