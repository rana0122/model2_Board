<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.BoardDTO" %>
<%@ page import="dao.BoardDAO" %>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br><br>
<a href="./BoardForm.do">글작성</a>
<table border=1 align=center width=500>
	<tr><td>번호</td>
			<td>작성자</td>
			<td>제목</td>
	</tr>
	
	<c:set var="num" value="${listcount-(page-1)* 10}"/>
	<c:forEach var="b" items="${boardlist}">	
	<tr><td>
					<c:out value="${num}"/>
					<c:set var="num" value="${num-1}" />
			</td>
			<td>${b.writer}</td>
			<td>
				<a href="./BoardContent.do?no=${b.no}&page=${page}">
					${b.subject}
				</a>
			</td>
	</tr>	
	</c:forEach>	


</table>


<!-- 페이지 출력 -->
<center>

<c:if test="${listcount > 0}">
		
		<c:if test="${startPage < 10}">
				[이전]
		</c:if>
		<c:if test="${startPage > 10}">
				<a href="./BoardList.do?page="${startPage-10}">
				[이전]</a>
		</c:if>		

		
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:if test="${i == page}">
					${i}
			</c:if>
			<c:if test="${i != page}">
					<a href="./BoardList.do?page=${i}">
					${i}</a>
			</c:if>		
		</c:forEach>		

		<c:if test="${endPage >= pageCount}">
			[다음]
		</c:if>
		<c:if test="${endPage < pageCount}">
			<a href="./BoardList.do?page=${startPage+10}">
			[다음]</a>
		</c:if>
		
</c:if>

</center>

</body>
</html>