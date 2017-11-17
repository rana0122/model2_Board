package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardUpdate");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter pw =response.getWriter();
		
		int no = Integer.parseInt(request.getParameter("no"));
		String page = request.getParameter("page");
		
		BoardDTO board = new BoardDTO();
		board.setNo(no);
		board.setWriter(request.getParameter("writer"));
		board.setPasswd(request.getParameter("passwd"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.update(board);
		if(result==1) {
			System.out.println("Update Succeed");
		}else if(result==-1){
			System.out.println("Update Fail(password is not correct)");
			pw.println("<script>");
			pw.println("alert('Update Fail(password is not correct)')");
			pw.println("history.go(-1)");
			pw.println("</script>");
			pw.close();
		}else {
			System.out.println("Update Fail(DB problems)");
		}

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		System.out.println("page : "+page);
//		forward.setPath("./BoardList.do?page="+page);
		forward.setPath("./BoardContent.do?page="+page+"&no="+no);

		return forward;
	}

}
