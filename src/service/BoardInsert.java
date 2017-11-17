package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardInsert implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardInsert");
		
		request.setCharacterEncoding("utf-8");
		
		BoardDTO board = new BoardDTO();
		board.setWriter(request.getParameter("writer"));
		board.setPasswd(request.getParameter("passwd"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.insertBoard(board);
		System.out.println("result="+result);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardList.do");		
		
		return forward;
	}

}
