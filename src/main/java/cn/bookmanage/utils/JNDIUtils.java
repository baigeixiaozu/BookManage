package cn.bookmanage.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



/**
 * 提供数据库驱动加载、连接数据库、释放资源等操作。
 * 
 * @author jiyec
 *
 */
public class JNDIUtils {

	public static PreparedStatement pstmt;

	public static Connection getConnection() throws NamingException, SQLException {
		Context ctx;
		ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/BMDS");
		return ds.getConnection();
	}
	
	public static int executeUpdate(Connection connection, String sql, Object[] params) {
		try {
			pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			for(int i=0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			int count = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				//获取插入数据的Id(主键，自增长)
				int cId = rs.getInt(1) ;
				// 返回这个Id
				return cId ;
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public static ResultSet executeQuery(Connection connection, String sql, Object[] params){
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			for(int i=0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public static void closeAll(ResultSet rs, Statement stmt, Connection connection) {
		try {
			if(rs != null)rs.close();
			if(stmt != null)stmt.close();
			if(connection != null)connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
