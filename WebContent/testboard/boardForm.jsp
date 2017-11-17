<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글작성</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/board.js"></script>
</head>
<body>

<form method=post action="/model2_board/BoardInsert.do">
	<table border=1 width=500 align=center>
		<caption>글 작 성</caption>
		<tr><td>작성자</td>
				<td><input type=text id=writer name=writer></td>
		</tr>		
		<tr><td>비밀번호</td>
				<td><input type=password id=passwd name=passwd></td>
		</tr>
		<tr><td>제목</td>
				<td><input type=text id=subject name=subject></td>
		</tr>
		<tr><td>내용</td>
				<td><textarea cols=50 rows=10 id=content name=content></textarea></td>	
		</tr>
		<tr><td colspan=2 align=center>
					<input type=submit value="등록">
					<input type=reset value="취소">
				</td>
		</tr>
	</table>

</form>



</body>
</html>