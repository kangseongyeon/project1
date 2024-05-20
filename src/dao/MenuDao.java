package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MenuDao {
	private static MenuDao instance;

	private MenuDao() {

	}

	public static MenuDao getInstance() {
		if (instance == null) {
			instance = new MenuDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<Map<String, Object>> menuList() {
		String sql = " SELECT MENU_NO, MENU_DELYN, MENU_NAME, MENU_PRICE, MENU_DETAIL\r\n" + "FROM MENU ";
		return jdbc.selectList(sql);
	}

	public void menuinsert(List<Object> param) {
		String sql = " INSERT INTO MENU\r\n" + " VALUES(?, ?, ?, ?, 'N') ";

		jdbc.update(sql, param);
	}

	public void menuUpdate(List<Object> param, int sel) {
//      String sql = " UPDATE MENU \r\n " + 
//            " SET MENU_NO = ?, MENU_NAME = ?, MENU_PRICE = ?, MENU_DETAIL = ?\r\n " + 
//            " WHERE MENU_NO = ? ";
//      jdbc.update(sql, param);

		String sql = " UPDATE MENU \r\n " + " SET ";
		if (sel == 1 || sel == 4) {
			sql += " MENU_NAME = ?";
			if (sel == 4)
				sql += ", ";
		} else if (sel == 2 || sel == 4) {
			sql += " MENU_PRICE = ?";
			if (sel == 4)
				sql += ", ";
		} else if (sel == 3 || sel == 4) {
			sql += "MENU_DETAIL = ?";
		}
		sql += " WHERE MENU_NO = ?";
		jdbc.update(sql, param);
	}

	public int menuDelete(List<Object> param) {
		String sql = " UPDATE MENU\r\n"
				+ "SET MENU_DELYN = 'Y'\r\n"
				+ "WHERE MENU_NO = ? ";
		return jdbc.update(sql, param);
	}

	public List<Map<String, Object>> menuInformation(List<Object> param) {
		String sql = "SELECT MENU_NO, MENU_DELYN, MENU_NAME, MENU_PRICE, MENU_DETAIL \r\n" + 
					 "FROM MENU\r\n" +
					 "WHERE MENU_NO = ?";
		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> detail(List<Object> param) {
		String sql = "SELECT * \r\n" + "FROM MENU\r\n" + "WHERE MENU_NO = ?";
		return jdbc.selectList(sql, param);
	}

	public List <Map<String, Object>> menuPaging(List<Object> param) {
		String sql = "SELECT * \r\n"
				+ "FROM (\r\n"
				+ "    SELECT MENU.*, ROWNUM AS RN\r\n"
				+ "    FROM MENU\r\n"
				+ ")\r\n"
				+ "WHERE (RN >= ? AND RN <= ?)";
		return jdbc.selectList(sql, param);
	}
}