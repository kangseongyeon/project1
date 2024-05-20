package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class AdminDao {
	private static AdminDao instance;

	private AdminDao() {

	}

	public static AdminDao getInstance() {
		if (instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public Map<String, Object> adminLogin(List<Object> param) {
		String sql = " SELECT ADMIN_NAME\r\n"
				+ "FROM ADMIN\r\n"
				+ "WHERE ADMIN_ID = ? \r\n"
				+ "      AND ADMIN_PW = ? \r\n"
				+ "      AND ADMIN_DELYN = 'N' \r\n"
				+ "      AND MEM_GU = ?";
		return jdbc.selectOne(sql, param);
	}

	public List<Map<String, Object>> findAdminId(List<Object> param) {
		String sql = " SELECT ADMIN_ID\r\n"
				+ "FROM ADMIN\r\n"
				+ "WHERE ADMIN_NAME = ? \r\n"
				+ "      AND ADMIN_TELNO = ? \r\n"
				+ "      AND MEM_GU = '0'";
		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> findAdminPw(List<Object> param) {
		String sql = " SELECT ADMIN_PW\r\n"
				+ "FROM ADMIN\r\n"
				+ "WHERE ADMIN_NAME = ? \r\n"
				+ "      AND ADMIN_ID = ?\r\n"
				+ "      AND MEM_GU = '0'";
		return jdbc.selectList(sql, param);
	}
	

	public void adminSign(List<Object> param) {
//		--- ID, PW, NAME, TELNO, BIR, GU, DELYN
		String sql = "INSERT INTO ADMIN \r\n"
				+ "VALUES (?, 'svt13', ?, ?, ?, ?, 'N')";
		jdbc.update(sql, param);
	}
	
	public List<Map<String, Object>> adminPw() {
		String sql = "SELECT ADMIN_PW\r\n"
				+ "FROM ADMIN\r\n"
				+ "WHERE ADMIN_ID = '202404_CSC'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> adminList() {
		String sql = "SELECT ADMIN_NAME, ADMIN_ID, ADMIN_PW, ADMIN_TELNO, ADMIN_BIR, ADMIN_DELYN\r\n"
				+ "FROM ADMIN";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> adminId(List<Object> param) {
		String sql = "SELECT ADMIN_ID\r\n"
				+ "FROM ADMIN\r\n"
				+ "WHERE ADMIN_ID = ?\r\n"
				+ "      AND MEM_GU = '0'";
		return jdbc.selectList(sql, param);    
	}

	public int adminDelete(List<Object> param) {
		String sql = "UPDATE ADMIN\r\n"
				+ "SET  ADMIN_DELYN = 'Y'\r\n"
				+ "WHERE ADMIN_ID = ? AND ADMIN_PW = ?";
		return jdbc.update(sql, param);
	}

	public List<Map<String, Object>> adminInformation(List<Object> param) {
		String sql = "SELECT ADMIN_NAME, ADMIN_ID, ADMIN_PW, ADMIN_TELNO, ADMIN_BIR, ADMIN_DELYN\r\n"
				+ "FROM ADMIN\r\n"
				+ "WHERE ADMIN_ID = ?";
		return jdbc.selectList(sql, param);
	}

	public void adminUpdate(List<Object> param, int sel) {
	      String sql = " UPDATE ADMIN "
	                + " SET ";
	       if (sel == 1 || sel == 4) {
	          sql += " ADMIN_NAME= ?";
	          if (sel == 4) sql += ", ";
	       } else if (sel == 2 || sel == 4) {
	          sql += " ADMIN_ID= ?";
	          if (sel == 4) sql += ", ";
	       } else if (sel == 3  || sel == 4) {
	          sql += " ADMIN_TELNO= ?";
	       }
	       sql += " WHERE ADMIN_ID = ?";
	       jdbc.update(sql, param);
	   }

}
