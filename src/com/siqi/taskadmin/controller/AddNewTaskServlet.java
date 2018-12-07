package com.siqi.taskadmin.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.siqi.taskadmin.bean.Task;
import com.siqi.taskadmin.dao.TaskDAO;
import com.siqi.taskadmin.dao.TaskDAOImpl;
import com.siqi.taskadmin.util.DataUtil;

/**
 * Servlet implementation class AddNewTaskServlet
 */
public class AddNewTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request. setCharacterEncoding("UTF-8");
		String newTitle=request.getParameter("newTitle");
		String newDuedate=request.getParameter("newDuedate");
		Date newDuedateDate=DataUtil.createDate(newDuedate);
		String newProject=request.getParameter("newProject");
		String statusString=request.getParameter("newStatus");
		Boolean status=(statusString.equals("1")?true:false);
		System.out.println(newTitle);
		System.out.println(newDuedate);
		System.out.println(newDuedateDate);
		System.out.println(newProject);
		System.out.println(statusString);
		System.out.println(status);
		Task task=new Task(newTitle,newDuedateDate,newProject,status);
		
		TaskDAO taskDAO= new TaskDAOImpl();
		taskDAO.addTask(task);
		response.sendRedirect("ShowAllTasksServlet");
		//request.getRequestDispatcher("ShowAllTasksServlet").forward(request, response);
	}

}
