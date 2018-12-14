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
<h2>게시글 상세보기</h2>
<form method="post">
<input type ="hidden" name="b_seq" value="${dto.b_seq}" />
<input type ="hidden" name="page" value="${cri.page}" />
<input type ="hidden" name="perPageNum" value="${cri.perPageNum}" />
</form>
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
		<td><fmt:formatDate value="${dto.b_regdate}" pattern="yyyy-MM-dd a HH:mm"/></td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${dto.b_title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly">${dto.b_content}</textarea>
	</tr>
	<tr>
		<td colspan="2">
			<button type="submit" onclick="updateForm('${dto.b_seq}')">수정</button>
			<button type="submit" onclick="delBoard('${dto.b_seq}')">삭제</button>
			<button type="submit" onclick="location.href='boardlist.do?page=${cri.page}&perPageNum=${cri.perPageNum}'">글목록</button>
		</td>
	</tr>
</table>

<script type="text/javascript">
	//수정폼으로 이동하는 함수
	function updateForm(b_seq){
		location.href="updateform.do?page=${cri.page}&perPageNum=${cri.perPageNum}&b_seq="+b_seq;
	}
	//게시글 삭제로 이동
	function delBoard(b_seq){
		location.href="delboard.do?page=${cri.page}&perPageNum=${cri.perPageNum}&b_seq="+b_seq;
	}
</script>
</body>
</html>