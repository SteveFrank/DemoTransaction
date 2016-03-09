package cn.itcast.demo;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import cn.itcast.jdbc.JdbcUtils;

public class Demo1 {
	/**
	 * 转账方法
	 * 所有Connection的操作都在Service层进行的处理
	 * 明天对于这一个问题进行相关的处理
	 * @param from
	 * @param to
	 * @param balance
	 * @throws SQLException 
	 */
	public void zhuanZhang(String from,String to,double balance) throws SQLException{
		//对事物的操作必须使用Connection对象
		Connection conn = JdbcUtils.getConnection();
		try {
			//（设置为false则是开启了事务）开启事务
			conn.setAutoCommit(false);
			
			AccountDao dao = new AccountDao();
			dao.updateBalance(conn,from,-balance);
			if(true){
				throw new RuntimeException("不好意思出现异常");
			}
			dao.updateBalance(conn,to,+balance);
			//提交事务
			conn.commit();
			System.out.println("提交事务成功。");
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("提交事务出错，开始回滚事务。");
			//回滚事务
			try {
				conn.rollback();
				System.out.println("回滚事务成功，表项未发生修改。");
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				System.out.println("回滚事务出错。");
			}
			throw new RuntimeException(e);
		} 
	}
	
	@Test
	/**
	 * 测试方法
	 * @throws SQLException
	 */
	public void fun1() throws SQLException{
		zhuanZhang("zs","ls",100);
	}
}
