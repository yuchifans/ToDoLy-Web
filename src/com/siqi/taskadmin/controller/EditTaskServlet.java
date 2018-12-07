package com.siqi.taskadmin.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.siqi.taskadmin.dao.TaskDAO;
import com.siqi.taskadmin.dao.TaskDAOImpl;
import com.siqi.taskadmin.util.DataUtil;

/**
 * Servlet implementation class EditTaskServlet
 */

public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request. setCharacterEncoding("UTF-8");
		int taskid=Integer.parseInt(request.getParameter("taskid"));
		//String title=request.getParameter("title");
		String newDuedate=request.getParameter("dueDate");
		Date newDuedateDate=DataUtil.createDate(newDuedate);
		String newProject=request.getParameter("project");
		String statusString=request.getParameter("status");
		Boolean status=(statusString.equals("1")?true:false);
		TaskDAO taskDAO= new TaskDAOImpl();
		taskDAO.updateTask(taskid,newDuedateDate,newProject,status);
		//request.getRequestDispatcher("ShowAllTasksServlet").forward(request, response);
		response.sendRedirect("ShowAllTasksServlet");
		
	}

}
