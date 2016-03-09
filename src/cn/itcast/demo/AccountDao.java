package cn.itcast.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cn.itcast.jdbc.JdbcUtils;

public class AccountDao {
	/**
	 * 修改指定用户的余额
	 * @param name
	 * @param balance
	 */
	public void updateBalance(Connection conn,String name,double balance) throws SQLException{
		try{
			/*
			 * 1、得到连接对象
			 * 要保证同一个连接
			 */
//			Connection conn = JdbcUtils.getConnection();
			/*
			 * 2、准备SQL语句
			 */
			String sql = "UPDATE account SET balance = balance + ? WHERE NAME = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			/*
			 * 3、参数赋值
			 */
			pstmt.setDouble(1, balance);
			pstmt.setString(2, name);
			/*
			 * 4、执行语句
			 */
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
