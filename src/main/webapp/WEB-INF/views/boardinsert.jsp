<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기 페이지</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-Latest.js"></script>
<script type="text/javascript">
	//$function(){} ->onload ,$(document).ready())
	$(function(){
		//form에서 submit이벤트가 발생하면 function()기능 실행~~~
		$("form").submit(function(){
			var tds = $("form table td"); //[td,td,td,td] 
			var tdVals = tds.slice(0,tds.length-1)   //마지막 td를 제외시키기위해 --> [td,td,td]
			var bool = true;
			tdVals.each(function(){     //each --> 배열의 길이만큼 함수를 실행
				if($(this).children().eq(0).val()==""){  //this --> 첫번째실행은 첫번째td  두번째 실행은 두번쨰td ...td안에 첫번째요소의 val값이 ""인지 확인
					alert($(this).prev("th").text() + "를 입력해주세요!");
					$(this).children().eq(0).focus();
					bool = false;
					return false;
				}        
			});//each
			return bool;
		});//submit
	});
</script>
</head>
<body>
<div>
<h2>게시글 작성</h2>
    <form action="insertboard.do" method="post">
        <table class="table table-striped table-hover">
        	<col width="100px"><col width="400px">
        	<tr>
        		<th>작성자</th>
        		<td><input type="text" name="m_id" /></td>
        	</tr>
        	<tr>
        		<th>제목</th>
        		<td><input type="text" name="b_title" /></td>
        	</tr>
        	<tr>
        		<th>내용</th>
        		<td><textarea rows="10" cols="50" name="b_content"></textarea></td>
        	</tr>
        	<tr>
        		<td colspan="2">
        			<input type="submit" value="글 등록" />
        		</td>
        	</tr>
        </table>
    </form>

</div>
</body>
</html>