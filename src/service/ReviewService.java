package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ReviewDao;
import util.ScanUtil;
import util.View;

public class ReviewService {
	private static ReviewService instance;

	private ReviewService() {

	}

	public static ReviewService getInstance() {
		if (instance == null) {
			instance = new ReviewService();
		}
		return instance;
	}
	
	
	ReviewDao reviewDao = ReviewDao.getInstance();
	

//	리뷰 전체 출력
	public List<Map<String, Object>> reviewList() {
		return reviewDao.reviewList();
	}

	public void reviewInsert(List<Object> param) {
		reviewDao.reviewInsert(param);
	}

	public void reviewUpdate(List<Object> param, int sel) {
		reviewDao.reviewUpdate(param, sel);
	}

	public int reviewDelete(List<Object> param) {
		return reviewDao.reviewDelete(param);
	}

	public List<Map<String, Object>> detail(List<Object> param) {
		return reviewDao.detail(param);
	}

	 public List<Map<String, Object>> reviewInformation(List<Object> param) {
	      return reviewDao.reviewInformation(param);
	   }

}