package com.siqi.taskadmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.siqi.taskadmin.bean.Task;
//import com.siqi.taskadmin.util.DataUtil;

public class TaskDAOImpl implements TaskDAO {

	@Override
	public void addTask(Task task) {
		Connection conn = DBConnector.getConnecttion();
		String sql = "insert into Task(title,duedate,project,status) values(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, task.getTitle());
			ps.setDate(2, new java.sql.Date(task.getDuedate().getTime()) );
			ps.setString(3, task.getProject());
			ps.setBoolean(4, task.getStatus());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTask(int taskid) {
		Connection conn = DBConnector.getConnecttion();
		String sql="delete from Task where taskid=?";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, taskid);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Task> getTaskList() {
		Connection conn = DBConnector.getConnecttion();
		String sql = "select * from Task order by status, dueDate";
		List<Task> list = new ArrayList<Task>();
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int taskid=rs.getInt("taskid");
				String title=rs.getString("title");
				Date dueDate=rs.getDate("dueDate");
				String project=rs.getString("project");
				boolean status=rs.getBoolean("status");
				list.add(new Task(taskid,title,dueDate,project,status));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Task getSingleTask(int taskid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTask(int taskid,Date newDuedate, String newProject, boolean status){
		// TODO Auto-generated method stub
		Connection conn = DBConnector.getConnecttion();
		String sql = "update task set dueDate=?, project=? ,status=? where taskid=?";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(newDuedate.getTime()));
			ps.setString(2, newProject);
			ps.setBoolean(3, status);
			ps.setInt(4, taskid);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		TaskDAOImpl taskDao=new TaskDAOImpl();
		taskDao.getTaskList();
	}

}
