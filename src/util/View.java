package util;

import java.util.ArrayList;
import java.util.List;

public enum View {
//	--- 로그인 관리 ---
	HOME,						// 홈	
	LOGIN,						// 로그인
	
//	--- 회원 ---
	MEMBER_SIGN,				// 2. 멤버 회원가입
	MEMBER_LOGIN,				// 1. 회원
	MEMBER_DELETE,				// 1-1. 멤버 탈퇴			
	MEMBER_FINDID,				// 1-2. 멤버 아이디 찾기
	MEMBER_FINDPW,				// 1-3. 멤버 패스워드 찾기
	MEMBER_UPDATE,				// 1-4. 멤버 수정
	
	
//	--- 예약 관리 ---
	RESERVE,					// 0. 전체 예약관리 시스템
	RESERVATION,				// 1. 실제 예약
	NOTICE,						// 2. 공지 출력
	REVIEW_LIST,				// 3. 리뷰 리스트 출력
	
//	--- 리뷰 관리 ---
	REVIEW,						// 리뷰 관리
	REVIEW_INSERT,				// 리뷰 작성
	REVIEW_UPDATE,				// 리뷰 수정
	REVIEW_DELETE,				// 리뷰 삭제
	

//	--- 관리자 관리 ---
	ADMIN,						// 관리자
	ADMIN_DELETE,				// 0. 관리자 삭제
	ADMIN_LOGIN,				// 1. 관리자 로그인
	ADMIN_FINDID,				// 2. 관리자 아이디 찾기
	ADMIN_FINDPW,				// 3. 관리자 패스워드 찾기
	ADMIN_UPDATE,				// 4. 관리자 수정
	ADMIN_SIGN,					// 5. 관리자 회원가입
	
//	--- 관리자 관리 ---
	ADMIN_HOME		,			// 관리자 메인 화면
	ADMIN_LIST,					// 관리자 조회
	ADMIN_MANAGEMENT,			// 관리자 직원 관리
	ADMIN_RESERVE,				// 관리자 예약 관리
	
//	--- 메뉴 관리 ---
	MENU,						// 메뉴 관리
	MENU_LIST,					// 메뉴 조회
	MENU_INSERT,				// 메뉴 작성
	MENU_UPDATE,				// 메뉴 수정
	MENU_DELETE,				// 메뉴 삭제
	PAGING,						// 페이징 기법
	
	
//	--- 음식 주문 ---
	ORDER,						// 음식 주문 홈
	ORDER_LIST,					// 최신 주문 내역 조회
	ORDER_MENU,					// 메뉴 선택
	ORDER_PAYMENT				// 결제
}