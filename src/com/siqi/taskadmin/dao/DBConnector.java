package com.siqi.taskadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	public static Connection getConnecttion() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Todoly?useUnicode=true&characterEncoding=UTF8&serverTimezone=Europe/Stockholm&useSSL=false",
					"root", "yuchifans");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		System.out.println(getConnecttion());
	}
}
