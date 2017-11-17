<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.BoardDTO" %>
<%@page import="dao.BoardDAO" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글수정</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./testboard/js/board.js"></script>
</head>
<body>

<form method=post action="/model2_board/BoardUpdate.do">
	<input type=hidden name=no value="${board.no }">
	<input type=hidden name=page value="${page}">
	<table border=1 width=500 align=center>
		<caption>글 수 정</caption>
		<tr><td>작성자</td>
				<td><input type=text id=writer name=writer value="${board.writer }"></td>
		</tr>		
		<tr><td>비밀번호</td>
				<td><input type=password id=passwd name=passwd></td>
		</tr>
		<tr><td>제목</td>
				<td><input type=text id=subject name=subject value="${board.subject }"></td>
		</tr>
		<tr><td>내용</td>
				<td><textarea cols=50 rows=10 id=content name=content>${board.content }</textarea></td>	
		</tr>
		<tr><td colspan=2 align=center>
					<input type=submit value="수정">
					<input type=reset value="취소">
				</td>
		</tr>
	</table>

</form>



</body>
</html>