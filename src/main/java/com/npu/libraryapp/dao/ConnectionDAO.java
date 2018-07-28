package com.npu.libraryapp.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionDAO
{
	static Connection conn;

	public static Connection getConnection()
	{
		try
		{
			String url = System.getenv("jdbc.url");// e.g. jdbc:mysql://localhost:3306/
			String uname = System.getenv("jdbc.username");//username
			String pwd = System.getenv("jdbc.password"); //password
			
			//MySQL jdbc driver
			Class.forName(System.getenv("jdbc.driver_class"));
			try
			{
				if(null==conn){
					conn = DriverManager.getConnection(url,uname,pwd);
				}
			}
			catch (SQLException ex)
			{
				System.out.println("SQL Exception occurred while getting connection object. \nDetails : "+ ex.getMessage());
			}
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Exception occurred while getting connection object. \nDetails : "+ e.getMessage());
		}
		return conn;
	}
	
	
	public static Statement getStatement()
	{
		Statement stmt = null;
		try
		{
			Connection connection = ConnectionDAO.getConnection();
			stmt=connection.createStatement();
		}
		catch (SQLException e) {
			System.out.println("Exception occurred while getting Statement object. \nDetails : "+ e.getMessage());
		}
		return stmt;
	}


}
