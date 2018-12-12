<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글목록보기</title>
<style type="text/css">
	table a{
		text-decoration: none;
	}
	table a:hover{color:red};
</style>
</head>
<body>
<h1>글목록 보기</h1>
<table >
	<col width = 100px>
	<col width = 100px>
	<col width = 300px>
	<col width = 300px>
	<col width = 50px>
	<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:choose>
		<c:when test="${empty list}">
			<tr><td colspan="10">--------작성된 글이 없습니다--------</td></tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.b_seq}</td>
					<td>${dto.m_id}</td>
					<td><a href="detailboard.do?b_seq=${dto.b_seq}">${dto.b_title}</a></td>
					<td>${dto.b_regdate}</td>
					<td>${dto.b_readcount}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
<!-- 	<tr> -->
<!-- 		<td colspan="10"> -->
<!-- 			<a href="insertform.do">글쓰기</a> -->
<!-- 		</td> -->
<!-- 	</tr> -->
</table>
<div>            
    <a href='insertform.do' id="write">글쓰기</a>            
</div>
</body>
</html>