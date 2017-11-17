<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="testboard.BoardDAO" %>

<% request.setCharacterEncoding("utf-8");%>
<%
//	BoardDTO	 board = new BoardDTO();

%>
<jsp:useBean id="board" class="testboard.BoardDTO"/>
<jsp:setProperty name="board" property="*"/>

<%
	BoardDAO manager = BoardDAO.getInstance();
	int result = manager.insertBoard(board);

	if(result == 1){	
%>
		<script>
			alert("글작성 성공");
			location.href="boardlist.jsp"
		</script>

<%}else{ %>

		<script>
			alert("글작성 실패")
			history.go(-1);
		</script>

<%} %>
