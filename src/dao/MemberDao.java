package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MemberDao {
	// DB 관련된 sql문 처리
	private static MemberDao instance;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public Map<String, Object> memberLogin(List<Object> param) {

		String sql = "SELECT MEM_NAME\r\n" + "FROM MEMBER\r\n" + "WHERE MEM_ID = ? \r\n" + "      AND MEM_PW = ? \r\n"
				+ "      AND MEM_DELYN = 'N' \r\n" + "      AND MEM_GU = ?\r\n";

		return jdbc.selectOne(sql, param);

	}

	public List<Map<String, Object>> sign(List<Object> param) {
		String sql = "SELECT MEM_ID\r\n" + "FROM MEMBER\r\n" + "WHERE MEM_ID = ?";
		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> findId(List<Object> param) {
		String sql = " SELECT MEM_ID\r\n" + "FROM MEMBER\r\n" + "WHERE MEM_NAME = ? AND MEM_EMAIL = ? ";
		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> findPw(List<Object> param) {
		String sql = " SELECT MEM_PW\r\n" + "FROM MEMBER\r\n" + "WHERE MEM_ID = ? AND MEM_EMAIL = ?";
		return jdbc.selectList(sql, param);
	}

	public void memberSign(List<Object> param) {
//		-- MEMID / MEM_GU / MEM_NAME / MEM_PW / MEM_EMAIL / MEM_TELNO / MEM_BIR / MEM_DELYN / MEMSHIP_ID\r\n
		String sql = "INSERT INTO MEMBER\r\n" + "VALUES (?, ?, ?, ?, ?, ?, ?, 'N', 'MS001')";
		jdbc.update(sql, param);
	}

	public List<Map<String, Object>> myInformation(List<Object> param) {
		String sql = "SELECT MEM_ID, MEM_NAME, MEM_PW, MEM_EMAIL, MEM_TELNO, MEM_BIR, MEM_DELYN, MEMSHIP_ID\r\n"
				+ "FROM MEMBER\r\n" + "WHERE MEM_ID = ? AND MEM_PW = ?  AND MEM_GU = '1'";
		return jdbc.selectList(sql, param);
	}

	public int memberDelete(List<Object> param) {
		String sql = "UPDATE MEMBER\r\n" + "SET MEM_DELYN = 'Y'\r\n" + "WHERE MEM_ID = (\r\n" + "  SELECT MEM_ID\r\n"
				+ "  FROM MEMBER\r\n" + "  WHERE MEM_ID = ? AND MEM_PW = ?\r\n" + ")";
		return jdbc.update(sql, param);
	}

	public void modifyMember(List<Object> param) {
		String sql = "UPDATE MEMBER \r\n" + "SET MEM_EMAIL = ?\r\n" + "WHERE MEM_ID = ? AND MEM_PW = ?";
		jdbc.update(sql, param);

	}

	public List<Map<String, Object>> removeMem(List<Object> param) {
		String sql = " UPDATE MEMBER" + "SET MEM_DELYN = 'Y'" + "WHERE MEM_ID = ?";
		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> memberList(List<Object> param) {
		String sql = "SELECT MEM_ID, MEM_NAME, MEM_PW, MEM_EMAIL, MEM_TELNO, MEM_BIR, MEM_DELYN, MEMSHIP_ID\r\n"
				+ "FROM MEMBER\r\n" + "WHERE MEM_ID = ? AND MEM_PW = ?  AND MEM_GU = '1'";
		return jdbc.selectList(sql);
	}

	public void memberUpdate(List<Object> param, int sel) {
		String sql = " UPDATE MEMBER " + " SET ";
		if (sel == 1 || sel == 6) {
			sql += " MEM_ID= ?";
			if (sel == 6)
				sql += ", ";
		} else if (sel == 2 || sel == 6) {
			sql += " MEM_NAME= ?";
			if (sel == 6)
				sql += ", ";
		} else if (sel == 3 || sel == 6) {
			sql += " MEM_PW= ?";
			if (sel == 6)
				sql += ", ";
		} else if (sel == 4 || sel == 6) {
			sql += " MEM_EMAIL = ?";
			if (sel == 6)
				sql += ", ";
		} else if (sel == 5 || sel == 6) {
			sql += " MEM_TELNO = ?";
		}
		sql += " WHERE MEM_ID = ?";
		jdbc.update(sql, param);
	}

	public List<Map<String, Object>> memberInformation(List<Object> param) {
		String sql = "SELECT * \r\n" + "FROM MEMBER\r\n" + "WHERE MEM_ID = ?";
		return jdbc.selectList(sql, param);
	}
}
