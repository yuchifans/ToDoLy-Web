package com.siqi.taskadmin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.siqi.taskadmin.dao.TaskDAO;
import com.siqi.taskadmin.dao.TaskDAOImpl;

/**
 * Servlet implementation class DeleteTaskServlet
 */

public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int taskid=Integer.parseInt(request.getParameter("taskid"));
		TaskDAO taskDAO= new TaskDAOImpl();
		taskDAO.deleteTask(taskid);
		//request.getRequestDispatcher("ShowAllTasksServlet").forward(request, response);
		response.sendRedirect("ShowAllTasksServlet");
	}

}
