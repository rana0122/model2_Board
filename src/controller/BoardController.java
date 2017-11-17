package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.ActionForward;
import service.BoardContent;
import service.BoardDelete;
import service.BoardInsert;
import service.BoardList;
import service.BoardUpdate;
import service.BoardUpdateForm;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {

	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		System.out.println("RequestURI=" + RequestURI);
		System.out.println("contextPath=" + contextPath);
		System.out.println("command=" + command);

		Action action = null;
		ActionForward forward = null;

		// 글작성
		if (command.equals("/BoardInsert.do")) {
			try {
				action = new BoardInsert();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//글작성 폼
		}else if(command.equals("/BoardForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./testboard/boardForm.jsp");
			
		// 글목록	
		}else if(command.equals("/BoardList.do")) {
			try {
				action = new BoardList();
				forward = action.execute(request, response);				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 상세 페이지			
		}else if(command.equals("/BoardContent.do")) {
			try {
				action = new BoardContent();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 글수정 폼	
		}else if(command.equals("/BoardUpdateForm.do")) {
			try {
				action = new BoardUpdateForm();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		//글수정	
		}else if(command.equals("/BoardUpdate.do")) {
			try {
				action = new BoardUpdate();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 글삭제 폼	
		}else if(command.equals("/BoardDeleteForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./testboard/deleteForm.jsp");
		
		// 글삭제
		}else if(command.equals("/BoardDelete.do")) {
			try {
				action = new BoardDelete();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
			}
		}

	}// doProcess() end
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");

		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");

		doProcess(request, response);
	}

}
