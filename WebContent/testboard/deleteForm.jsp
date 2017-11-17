<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.BoardDTO" %>
<%@page import="dao.BoardDAO" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글삭제</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./testboard/js/board.js"></script>
</head>
<body>
no= ${param.no}
page= ${param.page}
<form method=post action="/model2_board/BoardDelete.do">
	<input type=hidden name=no value="${param.no}">
	<input type=hidden name=page value="${param.page}">
	<table border=1 width=500 align=center>
		<caption>글 삭 제</caption>		
		<tr><td>비밀번호</td>
				<td><input type=password id=passwd name=passwd></td>
		</tr>		
		<tr><td colspan=2 align=center>
					<input type=submit value="삭제">
					<input type=reset value="취소">
				</td>
		</tr>
	</table>

</form>



</body>
</html>