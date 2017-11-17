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
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String RequestURI= request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		System.out.println("RequestURI=" + RequestURI);
		System.out.println("contextPath=" + contextPath);
		System.out.println("command=" + command);
		
		Action action=null;
		ActionForward forward=null;
		//글작성
		if(command.equals("/BoardInsert.do")) {
			try {
				action =  new BoardInsert();//upcasting
				forward = action.execute(request, response);
			}catch(Exception e){System.out.println("BoardInset fail");}
		//글목록
		}else if(command.equals("/BoardList.do")) {
			try {
				action =  new BoardList();//upcasting
				forward = action.execute(request, response);
			}catch(Exception e) {
				
			}
		//글 작성폼으로 돌려주기
		}else if(command.equals("/BoardForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(true);//url 주소가 바뀌게 됨.(redirect 방식)
			forward.setPath("./testboard/boardForm.jsp");
		//상세페이지 확인
		}else if(command.equals("/BoardContent.do")) {
			try {
				action =  new BoardContent();//upcasting
				forward = action.execute(request, response);
			}catch(Exception e) {}
		//글수정폼으로 이동
		}else if(command.equals("/BoardUpdateForm.do")) {
			
			try {
				action =  new BoardUpdateForm();//upcasting
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//글 수정
		}else if(command.equals("/BoardUpdate.do")) {
			try {
				action =  new BoardUpdate();//upcasting
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 글삭제 폼	
		}else if(command.equals("/BoardDeleteForm.do")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./testboard/deleteForm.jsp");
		//글삭제
		}else if(command.equals("/BoardDelete.do")) {
			try {
				action =  new BoardDelete();//upcasting
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet method start");
		doProcess(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost method start");
		doProcess(request, response);
	}

}
