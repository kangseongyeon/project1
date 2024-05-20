package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class ReviewDao {
	private static ReviewDao instance;

	private ReviewDao() {

	}

	public static ReviewDao getInstance() {
		if (instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<Map<String, Object>> reviewList() {
	      String sql = "SELECT REVIEW_NO, MEM_ID, REVIEW_TITLE, REVIEW_CONTENT, REVIEW_SCORE, REVIEW_DELYN\r\n"
	            + "FROM REVIEW";
	      return jdbc.selectList(sql);
	   }

	public void reviewInsert(List<Object> param) {
	      String sql = " INSERT INTO REVIEW\r\n"
	            + "VALUES((SELECT NVL(MAX(REVIEW_NO),0 ) + 1 FROM REVIEW), ?, \r\n"
	            + "        (SELECT NVL(MAX(RESERVE_NO),0 ) + 1 FROM REVIEW), ?, \r\n"
	            + "        ?,  ?, SYSDATE, 'N')";
	      
	      jdbc.update(sql, param);
	      
	   }

	public void reviewUpdate(List<Object> param, int sel) {
		String sql = " UPDATE REVIEW "
					+ " SET ";
		if (sel == 1 || sel == 4) {
			sql += " REVIEW_TITLE = ?";
			if (sel == 4) sql += ", ";
		} else if (sel == 2 || sel == 4) {
			sql += " REVIEW_CONTENT = ?";
			if (sel == 4) sql += ", ";
		} else if (sel == 3) {
			sql += "REVIEW_SCORE = ?";
		}
		
		sql += " WHERE MEM_ID = ?";
		jdbc.update(sql, param);
	}

	public int reviewDelete(List<Object> param) {
		String sql = " UPDATE REVIEW\r\n"
				+ "SET REVIEW_DELYN = 'Y'\r\n"
				+ "WHERE MEM_ID = ? ";
		return jdbc.update(sql, param);
	}

	public List<Map<String, Object>> detail(List<Object> param) {
		String sql = "SELECT * \r\n"
				+ "FROM REVIEW\r\n"
				+ "WHERE MEM_ID = ?";
		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> reviewInformation(List<Object> param) {
		String sql = "SELECT REVIEW_NO, MEM_ID, REVIEW_TITLE, REVIEW_CONTENT, REVIEW_SCORE, REVIEW_DELYN\r\n"
				+ "FROM REVIEW\r\n"
				+ "WHERE MEM_ID = ?";
		return jdbc.selectList(sql, param);
	}
	   
}
