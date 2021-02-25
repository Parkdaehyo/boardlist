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


 		<script
 		src="https://code.jquery.com/jquery-3.5.0.min.js" 
		integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ="
 		crossorigin="anonymous"></script>
</head>

<style>

.page-active {
	background-color:#d2d2d2; 
}


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


table {
border-color: red;



}

 /* Basic table style */
           table {
           text-align: center;
        
            
           }
           
           
            
        
         
        
            /* Additional style */
            thead tr       { background-color: #cccccc; }
            td.center      { text-align: center; }
            td.right       { text-align: right; }
            tbody tr:nth-child(even)  { background-color: #D2E1FF; }
            tbody tr:nth-child(odd)   { background-color: #E8F5FF; }


    td {
        padding: 4px;
        border: 1px solid #333333;
      }



/* .child {

    position: absolute;
    top: 200px;
    left: 1000px;
    height: 40px;
    bottom:-20px;
}
 */

</style>


<!-- width: 100px; -->    

<body>





<br>




<table align="center" width="800" style="text-align:center; border-color: white;">

<tr style="background-color :#1E82FF">
<td id="A"><a href="<c:url value='/board/list/'/>" style="color:white;">일반게시판</a></td>
<td id="B"><a href="<c:url value='/board/list2/'/>" style="color:white;">첨부파일 게시판</a></td>
<td id="C"><a href="<c:url value='/board/list3/'/>" style="color:white;">다중 첨부파일 게시판</a></td>
<td id="D"><a href="<c:url value='/board/list4/'/>" style="color:white;">댓글 게시판</a></td>
</tr>
</table>

<br>
<br>

<script>


$(function() {
	
	
	$("#A").on("mouseenter", function() {
		
		$("#A").css({"background-color" : "yellow"});
		
	});
	
	$("#B").on("mouseenter", function() {
		
		$("#B").css({"background-color" : "yellow"});
		
		
	});
	
	$("#C").on("mouseenter", function() {
		
		$("#C").css({"background-color" : "yellow"});
		
		
	});
	
	$("#D").on("mouseenter", function() {
		
		$("#D").css({"background-color" : "yellow"});
		
		
	});
	
	
	
});






</script>

