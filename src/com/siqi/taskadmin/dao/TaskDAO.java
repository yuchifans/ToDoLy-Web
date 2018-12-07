package com.siqi.taskadmin.dao;

import java.util.Date;
import java.util.List;
import com.siqi.taskadmin.bean.Task;

public interface TaskDAO {
	public void addTask(Task task);

	public void deleteTask(int taskid);

	public List<Task> getTaskList();

	public Task getSingleTask(int taskid);

	public void updateTask(int taskid, Date dueDate,String project,boolean status);
}
