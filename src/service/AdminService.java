package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.AdminDao;
import dao.MemberDao;

public class AdminService {
	private static AdminService instance;

	private AdminService() {

	}

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}

	AdminDao adminDao = AdminDao.getInstance();

	// 관리자 로그인
	public boolean adminLogin(List<Object> param, int role) {
		Map<String, Object> member = adminDao.adminLogin(param);

		// 로그인 실패
		if (member == null) {
			return false;
		}
		if (role == 0) {
			// 0. 관리자
			MainController.sessionStorage.put("admin", member);
		}
		// 로그인 성공
		if (role == 1) {
			// 1. 일반 회원 -> member에 넣어줌
			MainController.sessionStorage.put("member", member);
		}

		return true;
	}

	// 관리자 아이디 찾기
	public List<Map<String, Object>> findAdminId(List<Object> param) {
		return adminDao.findAdminId(param);
	}

	// 관리자 비밀번호 찾기
	public List<Map<String, Object>> findAdminPw(List<Object> param) {
		return adminDao.findAdminPw(param);
	}

	// 관리자 회원가입
	public void adminSign(List<Object> param, int role) {
		adminDao.adminSign(param);
	}

	// 관리자 공통 비밀번호 찾기
	public List<Map<String, Object>> adminPw() {
		return adminDao.adminPw();
	}

	// 관리자 전체 출력
	public List<Map<String, Object>> adminList() {
		return adminDao.adminList();
	}

	// 관리자 삭제
	public List<Map<String, Object>> adminId(List<Object> param) {
		return adminDao.adminId(param);
	}

	public int adminDelete(List<Object> param) {
		return adminDao.adminDelete(param);

	}

	public List<Map<String, Object>> adminInformation(List<Object> param) {
		return adminDao.adminInformation(param);
	}

	public void adminUpdate(List<Object> param, int sel) {
		adminDao.adminUpdate(param, sel);
	}
}
