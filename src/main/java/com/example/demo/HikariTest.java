package com.example.demo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariTest {

	private HikariConfig config;
	private DataSource dataSource;
	static {
		HikariConfig config = new HikariConfig("/hikari.properties");
		DataSource dataSource = new HikariDataSource(config);
	}

	public static void main(String args[]) {

	}

	@Test
	public void findAll() throws SQLException {

		String sql = "insert into [dbo].[todo] values(?,?,?,?)";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			// 获得连接
			connection = dataSource.getConnection();
			// 开启事务设置非自动提交
			connection.setAutoCommit(false);
			// 获得Statement对象
			statement = connection.prepareStatement(sql);

            statement.setInt(1, 4);
            statement.setString(2, "555");
            statement.setString(3, "555");
            statement.setBoolean(4, true);
			// 提交事务
            statement.executeUpdate();
			connection.commit();
		} finally {
			// 释放资源
//        	dataSource.release(connection, statement, null);
		}
	}

}
