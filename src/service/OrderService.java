package service;

import java.util.List;
import java.util.Map;

import dao.OrderDao;

public class OrderService {
	private static OrderService instance;

	private OrderService() {

	}

	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}

	OrderDao orderDao = OrderDao.getInstance();

	public void orderInsert(List<Object> param) {
	    orderDao.orderInsert(param);
	}


	public List<Map<String, Object>> orderList(int reserveNum) {
	    return orderDao.orderList(reserveNum);
	}

	public List<Map<String, Object>> orderPayment(int reserveNum) {
		return orderDao.orderPayment(reserveNum);
	}
}
