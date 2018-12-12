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
<title></title>
</head>
<body>
<div>
<h2>게시글 작성</h2>
    <form action="insertboard.do" method="post">
        <table>
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