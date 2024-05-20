package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class ReserveDao {
	private static ReserveDao instance;

	private ReserveDao() {

	}

	public static ReserveDao getInstance() {
		if (instance == null) {
			instance = new ReserveDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<Map<String, Object>> noticeList() {
		String sql = "SELECT NT_NO, NT_TITLE, NT_CONTENT \r\n" + "FROM NOTICE";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> reservationSeat() {
		String sql = " SELECT * FROM SEAT WHERE SEAT_STATUS = 'Y' ";

		return jdbc.selectList(sql);
	}

	public void reservation(List<Object> param) {
		String sql = " INSERT INTO RESERVE(RESERVE_NO, SEAT_NO, RESERVE_NUM, RESERVE_DATE, RESERVE_TIME, MEM_ID, RESERVE_NAME, RESERVE_TELNO, RESERVE_EMAIL, RESERVE_REQUEST, RESERVE_DELYN)\r\n"
				+ "VALUES((SELECT NVL(MAX(RESERVE_NO),0) + 1 FROM RESERVE), (SELECT NVL(MAX(SEAT_NO),0) + 1 FROM RESERVE), ?, ?, ?, ?, ?, ?, ?, ?,'N') ";

		jdbc.update(sql, param);
	}

	public List<Map<String, Object>> reserveList() {
		String sql = "SELECT *" + "FROM RESERVE";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> resNo() {
		String sql = "SELECT RESERVE_NO\r\n" + "FROM RESERVE\r\n"
				+ "WHERE RESERVE_NO = (SELECT NVL(MAX(RESERVE_NO), 0) FROM RESERVE)";
		return jdbc.selectList(sql);
	}
}