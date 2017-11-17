<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.BoardDTO" %>
<%@page import="dao.BoardDAO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세 페이지</title>
</head>
<body>

<table border=1 width=500 align=center>
		<caption>상세 페이지</caption>
		<tr><td>작성자</td>
				<td>${board.writer}</td>
		</tr>		
		<tr><td>제목</td>
				<td>${board.subject}</td>
		</tr>
		<tr><td>내용</td>
				<td><pre>${board.content}</pre></td>	
		</tr>
		<tr><td colspan=2 align=center>
					<input type=button value="수정" onClick="location.href='./BoardUpdateForm.do?no=${board.no}&page=${page }' ">
					<input type=button value="삭제" onClick="location.href='./BoardDeleteForm.do?no=${board.no}&page=${page }' ">
				</td>
		</tr>
	</table>

</body>
</html>