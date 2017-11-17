package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;

public class BoardList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardList");
		
		int page=1;
		int limit=10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int startRow = (page-1) * limit + 1;
		int endRow = page * limit;
		
		List boardlist = new ArrayList();
		
		BoardDAO dao = BoardDAO.getInstance();
		int listcount = dao.getCount();
		boardlist = dao.selectBoard(startRow, endRow);
		
		int pageCount = listcount / limit + ((listcount%limit == 0)? 0 : 1);
				
		int startPage = ((page-1)/10) * limit +1;		
		
		int endPage = startPage +10 - 1;	
		
		if(endPage > pageCount) endPage = pageCount;		
		
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("listcount", listcount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("boardlist", boardlist);		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/testboard/boardlist.jsp");
		
		return forward;
	}

}
