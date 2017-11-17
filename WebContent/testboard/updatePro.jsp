<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="testboard.BoardDAO" %>

<% request.setCharacterEncoding("utf-8");%>

<jsp:useBean id="board" class="testboard.BoardDTO"/>
<jsp:setProperty name="board" property="*"/>

<%	
	String pageNum = request.getParameter("pageNum");

	BoardDAO manager = BoardDAO.getInstance();
	int result = manager.update(board);

	if(result == 1){	
%>
		<script>
			alert("글수정 성공");
			location.href="boardlist.jsp?pageNum="+<%=pageNum%>
		</script>

<%}else if(result==-1){ %>

		<script>
			alert("비밀번호가 틀렸습니다.")
			history.go(-1);
		</script>

<%} %>
