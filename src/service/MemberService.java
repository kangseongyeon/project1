package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.MemberDao;

public class MemberService {
	private static MemberService instance;

	private MemberService() {

	}

	public static MemberService getInstance() {
		if (instance == null) {
			instance = new MemberService();
		}
		return instance;
	}

	MemberDao memberDao = MemberDao.getInstance();
	

	// 멤버 로그인
	public boolean login(List<Object> param, int role) {
		Map<String, Object> member = memberDao.memberLogin(param);
		
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
	
	// 아이디 찾기
	public List<Map<String, Object>> findId(List<Object> param) {
		return memberDao.findId(param);
	}
	
	// 비밀번호 찾기
	public List<Map<String, Object>> findPw(List<Object> param) {
		return memberDao.findPw(param);
	}

	// 멤버 회원가입
	public void memberSign(List<Object> param, int role) {
		memberDao.memberSign(param);
	}
	
//	멤버 정보 출력
	public List<Map<String, Object>> myInformation (List<Object> param) {
		return memberDao.myInformation(param);
	}

//	멤버 삭제
	public int memberDelete (List<Object> param) {
		return memberDao.memberDelete(param);
	}

//	정보 수정
	public void modifyMember(List<Object> param) {
		memberDao.modifyMember(param);
		
	}

	public List<Map<String, Object>> removeMem(List<Object> param) {
		return memberDao.removeMem(param);
	}

	public List<Map<String, Object>> sign(List<Object> param) {
		return memberDao.sign(param);
	}

	public List<Map<String, Object>> memberList(List<Object> param) {
		return memberDao.memberList(param);
	}

	public void memberUpdate(List<Object> param, int sel) {
        memberDao.memberUpdate(param, sel);
     }

	public List<Map<String, Object>> memberInformation(List<Object> param) {
		return memberDao.memberInformation(param);
	}
}