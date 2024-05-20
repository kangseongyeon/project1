package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class OrderDao {
	private static OrderDao instance;

	private OrderDao() {

	}

	public static OrderDao getInstance() {
		if (instance == null) {
			instance = new OrderDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	
	// 메뉴, 수량 선택
	public void orderInsert(List<Object> param) {
		String sql = " INSERT INTO ORDER_MENU (ORDER_ID, RESERVE_NO, MENU_NO, MENU_QTY)\r\n"
				+ "VALUES ((SELECT NVL(MAX(ORDER_ID), 0) + 1 FROM ORDER_MENU), ?, ?, ?)";
		jdbc.update(sql, param);

	}

	// 전체 주문 메뉴 조회
	public List<Map<String, Object>> orderList(int reserveNum) {
	    String sql = "SELECT R.RESERVE_NO, O.MENU_NO, O.MENU_QTY " +
	                 "FROM RESERVE R, ORDER_MENU O " +
	                 "WHERE R.RESERVE_NO = O.RESERVE_NO AND O.RESERVE_NO = ?";
	    List<Object> param = new ArrayList<>();
	    param.add(reserveNum);
	    List<Map<String, Object>> result = jdbc.selectList(sql, param);
	    
	    return result;
	}
	
	// 결제
	public List<Map<String, Object>> orderPayment(int reserveNum) {
		String sql = "SELECT R.RESERVE_NO, SUM(M.MENU_PRICE * OM.MENU_QTY) TOTAL_PRICE\r\n"
				+ "FROM RESERVE R\r\n"
				+ "INNER JOIN ORDER_MENU OM ON R.RESERVE_NO = OM.RESERVE_NO\r\n"
				+ "INNER JOIN MENU M ON OM.MENU_NO = M.MENU_NO\r\n"
				+ "WHERE R.RESERVE_NO = ?\r\n"
				+ "GROUP BY R.RESERVE_NO";
	
		List<Object> param = new ArrayList<>();
	    param.add(reserveNum);
	    List<Map<String, Object>> result = jdbc.selectList(sql, param);
	
		return result;
	}


}
