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


<script>

<!-- 파일을 보이게 할 수 있도록 읽어주는 함수 -->

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
       	 
       	 var count = 0;
       //이게 각각의 파일선택이 어디에 업로드를 해줄지 위치를 찾아주는 역할을 한다
       	 $('#i_imageFileName'+ count).attr('src', e.target.result); 
       	 //$('#preview').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}  

var cnt=1;
function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	  cnt++;
}  

</script>
 <!--      width: 100%;-->

<body>


<br>

<table align="center" width="800" style="text-align:center; border-color: white;">

<tr style="background-color :#1E82FF">
<td ><a href="<c:url value='/board/list/'/>" style="color:white;">일반게시판</a></td>
<td ><a href="<c:url value='/board/list2/'/>" style="color:white;">첨부파일 게시판</a></td>
<td ><a href="<c:url value='/board/list3/'/>" style="color:white;">다중 첨부파일 게시판</a></td>
<td ><a href="<c:url value='/board/list4/'/>" style="color:white;">댓글 게시판</a></td>
</tr>
</table>

<br>
<br>







<!-- 

여백 지정
margin 5px 7px 3px 0px; (위, 오른쪽, 아래, 왼쪽 순)


 -->



 					<!--  form role == 부트스트랩 -->
 					<form id= "formObj" name="articleForm" method="post"  action="${contextPath}/board/write4" enctype="multipart/form-data">
 
					<!--  위치를 자동으로 고정하고있음
					<colgroup>
						<col style="width:12%;" /><col style="width:auto;" /><col style="width:12%;" /><col style="width:38%;" />
					</colgroup>
						 -->
					<table align="center">
						<tbody>
						
				<%-- 	   <c:forEach var="image" items="${imageFileList}"> 
						 <input type="hidden" name="imageFileNO" value="${image.imageFileNO}" />
						 <input type="hidden" name="imageFileNO1" value="${image.imageFileNO}" />
						 <input type="hidden" name="imageFileNO2" value="${image.imageFileNO}" />
						 	
						 	 	 	 	 	 	 
						</c:forEach>
						 --%>
							
						<!--  이 페이지는 
						
						저장과 수정이 같은 한 화면이기 때문에
						write2.jsp로 진입할시에 value의 aritlce 값들은
						전혀 표시가 안된다.
						 -->
						 <c:if test="${A == 1}">
						<input type="hidden" id="ABC" name="boardNo" value="${boardNo}" />
						</c:if>
							  	<c:if test="${A == 2}">
						<input type="hidden" id="DEF" name="boardNo" value="${article.boardNo}"/>	
						</c:if>
					
					
 					<%-- 	 <input id="boardno" type="hidden" name="boardNo" value="${article.boardNo}" /> --%>
 						 
 						 
 						 <!--  write.jsp에서 p객체가 넘어왔고 역시나 수정을 할때 페이지 객체정보가 넘어가기때문에
 						 
 						 /board/modify post요청에서 searchvo에서 조회문에 들어갔을때 저장된 페이지정보가
 						 셋팅된다.
 						 
 						  -->
 						  
 					
          				<input type="hidden" name="page" value="${p.page}">
          				<input type="hidden" name="countPerPage" value="${p.countPerPage}">
 						
 						
 						<!--  왜 제목에 width를 설정했는데 다 바뀌는거지? -->
						<tr>
							<td width="100"  scope="row"  align="center">제목</td>
							<td><input class="size" type="text" id="title" name="title" value="${article.title}" /></td>
						</tr>
						<tr>
						    <td scope="row"  width=30 align="center">작성자</td>
							<td><input class="size2" type="text" id="writer" name="writer" value="${article.writer}" /></td>						
						</tr>
						<tr>
							<td scope="row" width=30 align="center">내용</td>
							<td colspan="3" id=""><textarea id="content" name="content" cols="90" rows="10">${article.content}</textarea></td>
						
						</tr>
						
						
						
					<c:choose>
					
					<c:when test="${not empty imageFileList && imageFileList!='null' }">
					
					
					    <!-- 밖에다 써야함. -->
			   <c:set var="count" value="0"/>
	 		 <c:forEach var="item" items="${imageFileList}" varStatus="status" >
	 		 <input id="imageFileNO"type="hidden" name="imageFileNO" value="${item.imageFileNO}"/>

			    <td width="150" align="center" rowspan="2">
			          이미지${status.count }
			   </td>
			   <td>
			     <input id="original" type="hidden" name="originalFileName" value="${item.imageFileName}" />	
			     <!-- 추가 --> 
			     <c:set var="aa" value="preview${count }"/>
			   <!--   <c:out value="${aa}" /> -->	
			     <!-- id="preview"에서 ${aa}로 바꿈 -->				     	     
			    <img src="${contextPath}/download.do?boardNo=${article.boardNo}&imageFileName=${item.imageFileName}" id="${aa}" width="200"/><br>
			    	 <%--    <img src="${contextPath}/download.do?boardNo=${article.articleNO}&imageFileName=${item.imageFileName}" id="${aa}" /><br> --%>
			   </td>   
			  </tr>  
			  
			  

			  <tr>
			    
			    <td>  
			    
			    <!--  인자로  img 태그 id값도 보낸다. --> 
			       <input  class="selectimage" type="file"  name="file" id="i_imageFileName" onchange="readURL(this, '${aa}');"   />
			    </td>
			 </tr>
			 
			 
		
			
			
			
			 <!--  추가 -->
			 <c:set var="count" value="${count +1 }" />
			 <c:out value="${count }" />
			 
		</c:forEach>
					</c:when>
					
			
			<c:otherwise>
			
						<tr>
						<td width=30 align="center">첨부파일</td>
						
						
						<div class="file_input">
						<td>
						
						 <input type="button" id="file_route" name="file_route" value="파일추가" onClick="fn_addFile()"/>
					
						</div>
						</td>	
						</tr>
						
						<tr>
	      				<td colspan="4"><div id="d_file" name="All_file"></div></td>
	  					 
	  					<!--    <td><img  id="preview" src="#" width=200 /></td> -->
	  					 </tr>
	  					 
					
							<!-- <tr>  tr은 줄개행을 의미
							  <td scope="row"> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
			                <td scope="row" width=30><img id="preview" src="#"  width=200 height=200/></td>
							  </tr> -->
		
							<input type="hidden" id="A" value="${A}" />
			
			</c:otherwise>
					
		<%-- 				
						<tr>
						<td width=30 align="center">첨부파일</td>
						
						
						<div class="file_input">
						<td>
						
						 <input type="button" id="file_route" name="file_route" value="파일추가" onClick="fn_addFile()"/>
					
						</div>
						</td>	
						</tr>
						
						<tr>
	      				<td colspan="4"><div id="d_file" name="All_file"></div></td>
	      				
	  					 </tr>
	  					 
	  					 	<input type="hidden" id="A" value="${A}" /> --%>
					
				
					
					
					</c:choose>
					
					
					
					
	<%-- 	 <c:if test="${not empty imageFileList && imageFileList!='null' }">
                 <!-- 밖에다 써야함. -->
			   <c:set var="count" value="0"/>
	 		 <c:forEach var="item" items="${imageFileList}" varStatus="status" >
	 		 <input id="imageFileNO"type="hidden" name="imageFileNO" value="${item.imageFileNO}"/>

			    <td width="150" align="center" rowspan="2">
			          이미지${status.count }
			   </td>
			   <td>
			     <input id="original" type="hidden" name="originalFileName" value="${item.imageFileName}" />	
			     <!-- 추가 --> 
			     <c:set var="aa" value="preview${count }"/>
			   <!--   <c:out value="${aa}" /> -->	
			     <!-- id="preview"에서 ${aa}로 바꿈 -->				     	     
			    <img src="${contextPath}/download.do?boardNo=${article.boardNo}&imageFileName=${item.imageFileName}" id="${aa}" width="200"/><br>
			    	    <img src="${contextPath}/download.do?boardNo=${article.articleNO}&imageFileName=${item.imageFileName}" id="${aa}" /><br>
			   </td>   
			  </tr>  
			  
			
			  <tr>
			    
			    <td>  
			    
			    <!--  인자로  img 태그 id값도 보낸다. --> 
			       <input  class="selectimage" type="file"  name="file" id="i_imageFileName" onchange="readURL(this, '${aa}');"   />
			    </td>
			 </tr>
			 
			 
		
			
			
			
			 <!--  추가 -->
			 <c:set var="count" value="${count +1 }" />
			 <c:out value="${count }" />
			 
		</c:forEach>
		</c:if>
		 --%>
					<!--  empty(null), ! empty(not null) -->
					
					<!--  일단 수정시에도 이게 추가되야 되는데 일단은 수정부터 되는지 확인해보고 잘되면 이 기능을 활성화 시킬 것이다. -->
					
					
						
	
				
						
				<%-- 		<tr>
						<td width=30 align="center">첨부파일</td>
						
						
						<div class="file_input">
						<td>
						
						 <input type="button" id="file_route" name="file_route" value="파일추가" onClick="fn_addFile()"/>
					
						</div>
						</td>	
						</tr>
						
						<tr>
	      				<td colspan="4"><div id="d_file" name="All_file"></div></td>
	  					 
	  					<!--    <td><img  id="preview" src="#" width=200 /></td> -->
	  					 </tr>
	  					 
					
							<!-- <tr>  tr은 줄개행을 의미
							  <td scope="row"> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
			                <td scope="row" width=30><img id="preview" src="#"  width=200 height=200/></td>
							  </tr> -->
		
							<input type="hidden" id="A" value="${A}" />
							
				
							 --%>
						
						
						
						
						</tbody>
						
						
						
						
				
				<!-- 
					 <td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
							  </tr>
							  <tr>
			                <td><img  id="preview" src="#"/></td>
				
				 -->
						
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
		location.href='/board/list2?page=${p.page}' 
			+ '&countPerPage=${p.countPerPage}';	
		
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
			
			
			document.articleForm.action = "${contextPath}/board/write4"
			document.articleForm.submit();
			
		} else {
		
			const formElement = $("#formObj"); 
			
			formElement.attr("action" , "/board/modify4");//attr(속성 , 변경값 ) 태그의 내부 속성을 변경 , action 속성을 /board/modify로 변경
			formElement.attr("method", "post"); 
			formElement.submit();
		}
		
	});
	
		console.log("저장 버튼이 클릭됨");
		
	});
	
	
	
	
	
	
	  //var count = 0;
	
	/*   $("#i_imageFileName").click(function() {
		    
		  $('#i_imageFileName').attr{(
				'name' : 'file' + count++
				
				
				
		  }); */
		  
		  var count = 0;
		  
		  //.class로 접근하면 차례대로 변한다.
		  $(".selectimage").click(function() {
			    
			  //alert('');
			  
			  $('#i_imageFileName').attr(
					  "name" , "file" + count++
					);
		  
		  
				 
			  $('#i_imageFileName').attr(
					'id' , 'i_imageFileName' + count++
					
					); 		  
		  
		  
			 
		  	$('#imageFileNO').attr(
				'name' , 'imageFileNO' + count++
				
				); 	
	  
			//var cnt = 1;
			
			/*   $(".selectimage").click(function() {
				    
				  $('#i_imageFileName').attr(
						  "id" , "file" + count++
			  });
			
			 */
			
			
			
			
			
			
	  

		 	//$("#i_imageFileName").attr('id', 'i_#imageFileName'+ count++);
		 	
		 	//   var active = document.getElementById("i_imageFileName");
		 	 //  active.id = 'i_imageFileName' + count++;
		 	  
	  }); 	
	  
	  
	  $("#original").click(function() {
	  
	  $('#original').attr({
			'name' : 'originalFileName' + count++
			
			}); 	
	  });

	  

	
	
	
	
	
	
	
	
	
	
});

</script>


</html>