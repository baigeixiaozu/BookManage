package cn.bookmanage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import cn.bookmanage.entity.User;
import cn.bookmanage.utils.JNDIUtils;

/**
 * 提供将注册信息存储到数据库中学生表的操作。
 * 
 * @author jiyec
 *
 */
public class UserDao {
	private final String prefix = "bm_";

	// 添加用户， 暂时无用
	public boolean addStu(User user) {
		String sql = "INSERT INTO `User` (`id`, `name`, `password`) VALUES (NULL, ?, ?)";

		String[] params = {
			user.getLogin()
		};
		
		int id = -1;
		Connection connection;
		try {
			connection = JNDIUtils.getConnection();
			id = JNDIUtils.executeUpdate(connection, sql, params);
			
			JNDIUtils.closeAll(null, null, connection);
			return id != -1;
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 检查用户是否存在
	 *
	 * @param login 用户登录名
	 * @param pass 用户密码
	 * @return 存在返回用户对象 | 不存在返回null
	 *
	 */
	public User checkUser(String login, String pass){
		String sql = "SELECT ID,user_login,user_nickname,user_status,meta_value AS level FROM " + prefix + "user," + prefix + "usermeta WHERE " + prefix + "user.ID=" + prefix + "usermeta.user_id AND user_login=? AND user_pass=? AND meta_key='user_level'";
		Connection connection = null;
		ResultSet rs = null;
		User user = null;

		try {
			connection = JNDIUtils.getConnection();
			String[] params = {login, pass};
			rs = JNDIUtils.executeQuery(connection, sql, params);

			// 查到用户
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setLogin(login);
				user.setNickname(rs.getString("user_nickname"));
				user.setStatus(rs.getInt("user_status"));
				user.setLevel(rs.getInt("level"));
			}



		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}finally {
			JNDIUtils.closeAll(rs, JNDIUtils.pstmt, connection);
		}
		return user;
	}
}
