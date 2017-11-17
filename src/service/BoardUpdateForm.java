package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardUpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardUpdateForm");
		
		int no =Integer.parseInt(request.getParameter("no"));
		String page = request.getParameter("page");
		
		BoardDAO dao= BoardDAO.getInstance();
		BoardDTO board = dao.getContent(no);
		
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./testboard/updateForm.jsp");

		return forward;
	}

}
