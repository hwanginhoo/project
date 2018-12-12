<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div>
<h2>게시글 수정하기</h2>
<form action="boardupdate.do" method="post">
<input type="hidden" name="b_seq" value="${dto.b_seq}" />
	<table>
		<tr>
			<th>글 번호</th>
			<td>${dto.b_seq}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.m_id}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${dto.b_regdate}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="b_title" value="${dto.b_title }" />
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="b_content">${dto.b_content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정완료"/>
				<button type="button" onclick="location.href='boardlist.do'">글목록</button>
			</td>
		</tr>
	</table>		
</form>
</div>
</body>
</html>