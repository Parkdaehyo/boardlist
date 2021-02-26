<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />

 
<script>

var bno = '${article.boardNo}'; //${article.boardNo}은 Model이다. commentS.jsp는 detail.jsp에 삽입된 형태고, detail.jsp는 content.jsp에 include 액션태그가 적용된 상태이므로, content.jsp의 Model을 참조할 수 있다. 
//var article ='${article.writer}';
var writer = $('[name=writer]');
//alert(writer);
console.log(writer);
//var login ='${login.account}';
var reg_date = '${article.regDate}';

const today = new Date();
const year = today.getFullYear();
const month	= 0+today.getMonth()+1;
const date	= today.getDate();






//name 속성값의 commentInsertBtn
$('[name=commentInsertBtn]').click(function(){ //detail.jsp에 commentInsertBtn(등록 부분)을 클릭시 발생하는 클릭 이벤트처리
	//var login ='${login.account}';
	// serialize() : 입력된 모든Element(을)를 문자열의 데이터에 serialize 한다.
	var insertData = $('[name=commentInsertForm]').serialize(); //detail.jsp 18번 라인 commentInsertForm의 text내용을 전체 읽을 수 있는  serialize()메서드
    commentInsert(insertData); 
});
 
 
 
//댓글 목록 
function commentList(){
    $.ajax({ //자바스크립트를 이용해서 비동기식으로 서버와 통신하는 함수, 서버와 통신할때 json이라는 형식을 사용한다.
        url : '/comment/list', //전송할 URL ajax는 서버에서 직접 접근이 불가능함.
        type : 'get', 	//서버로 전송하는 데이터 타입(Post,Get)
        data : {'bno':bno}, // 서버에 전송할 데이터 key/value 형식
        success : function(data){ //통신에 성공했을때 호출할 스크립트 함수. commentController의 mCommentServiceList의 return 값이 결국 Mapper.xml 까지 정상적으로 도달해서 실행이 성공 됬을 경우,
            var a =''; //자바스크립트 식 변수 초기화, a에 공백값을 넣어주어 에러방지
            
            //해석: data라는 배열을 받아서
            $.each(data, function(key, value){ //$.each() 메서드는 매개 변수로 받은것을 사용해  for in 반복문과 같이 배열이나 객체의 요소를 검사할 수 있는 메서드이다.
            	
            	//자바스크립트에서 html 구문 삽입시키기
       			a += '<br>';
                a += '<div class="commentInfo'+value.cno+'">'+'작성자 : '+ value.writer; //댓글번호 : '+value.cno+' 
                a += '<a style="color: blue;" onclick="commentUpdate('+value.cno+',\''+value.content+'\',\''+value.writer+'\');"> 수정 </a>';
                a += '<a style="color: red;" onclick="commentDelete('+value.cno+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+value.cno+'"> <p> 내용 : '+value.content +'</p>';
                a += '<div class="commentRegDate'+value.cno+'"> <p> 작성시간 : '+ year +'-' + '0'+month + '-' + date; '</p>';
                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '</div></div>';
            
            });
            
            $(".commentList").html(a); //이부분에 삽입시켜라.
        }
    });
}
 
//댓글 등록
function commentInsert(insertData){
								//name값이 줄줄이.
	console.log("데이터확인좀" + insertData) 
	console.log("writer: " + insertData.writer)
	console.log("content: " + insertData.content)

  	var check = $('#writer2').val();
	var check2 = $('#content2').val();
	
	//콘솔에 찍어보니 null이 아니라 ""이 값이 들어갔구나.
	console.log(check);
	
							
	if(	check == "" || check2 == "") { 
		
		alert("입력정보를 확인해주세요!");
		
		return;
		
	}

    $.ajax({
    	
    	//등록시에 타는 매핑명
        url : '/comment/insert', 
        type : 'post',
        data : insertData,
        success : function(data){
        	/* if(login === null) {
        		console.log(login)@
        		alert("로그인을 해야해요!");
        	} */
        
            if(data == 1) {
                commentList(); //댓글 작성 후 댓글 목록 reload
                $('[name=content]').val('');
            }
        }
    });
}

 
//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(cno, content,writer){ //29번 라인에 의해서 매개값을 받음.
	
	console.log("수정시 작성자 이름: " + writer)
	console.log("수정시 작성자 내용: " + content)
	
    var a ='';
    
    a += '<div class="input-group">'; 
    a += '<input type="text" class="form-control" name="writer_'+cno+'" value="'+writer+'"/>';
    a += '<input type="text" class="form-control" name="content_'+cno+'" value="'+content+'"/>';
    																							//이 코드가 ajax와 통신하는 코드다.
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+cno+');">수정</button> </span>';
    a += '</div>';
    
    //a태그 값들이 여기에 전송되는데 이건 내용인데?? 그러면..
    		
    //ajax 타고 돌아와서 여기에 추가됨.
    $('.commentContent'+cno).html(a);
    //$('.commentInfo'+cno).html(a);

    
    
}
 
//댓글 수정




function commentUpdateProc(cno){
    var updateContent = $('[name=content_'+cno+']').val();
    var writerContent = $('[name=writer_'+cno+']').val();
    
    //const??
    
    const all_data = { "content": updateContent, "cno": cno,
    			    'writer' : writerContent, 'cno' : cno
    				};
			
    
    $.ajax({
        url : '/comment/update',
        type : 'post',
        data : all_data,
      
    
        success : function(data){
            if(data == 1) commentList(bno); //댓글 수정후 목록 출력 
        }
    });
}
 
//댓글 삭제 
function commentDelete(cno){
    $.ajax({
        url : '/comment/delete/'+cno,
        type : 'post',
        success : function(data){
            if(data == 1) commentList(bno); //댓글 삭제후 목록 출력 
        }
    });
}
 
 
 
 
$(document).ready(function(){ //이것을 주석처리 하니 처음에 댓글 목록에 진입했을시에 작성되었던 댓글이 안보이다가, 댓글을 작성하니 다시 보였다.
    commentList(); //페이지 로딩시 댓글 목록 출력 
});
 
 
 
</script>