package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardDelete");
		response.setContentType("text/html; charset+utf-8");
		request.setCharacterEncoding("utf-8");
		
		int no= Integer.parseInt(request.getParameter("no"));
		String page= request.getParameter("page");
		PrintWriter pw =response.getWriter();
		
		BoardDTO board = new BoardDTO();
		board.setNo(no);
		board.setWriter(request.getParameter("writer"));
		board.setPasswd(request.getParameter("passwd"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.delete(board);
		
		if(result==1) {
			System.out.println("delete succeed");
		}else if(result==-1) {
			System.out.println("passwd is not correct");
			pw.println("<script>");
			pw.println("alert('passwd is not correct')");
			pw.println("history.go(-1)");
			pw.println("</script>");
			pw.close();
			
		}else {
			System.out.println("db fail");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardList.do?page=1");
		return forward;
	}

}
