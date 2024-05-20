package view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Print {

	public void printLn(int num) {
		for (int i = 0; i < num; i++)
			System.out.println();
	}

	public void topVar(int num) {
		for (int i = 0; i <= num; i++) {
			System.out.print("⏔⏔⏔⏔⏔⏔⏔⏔⏔⏔");
		}
		System.out.println();
	}

	public void bottomVar(int num) {
		for (int i = 0; i <= num; i++) {
			System.out.print("⏕⏕⏕⏕⏕⏕⏕⏕⏕⏕");
		}
		System.out.println();
	}

	public void tap(int num) {
		for (int i = 0; i <= num; i++) {
			System.out.print("\t");
		}
	}

	public void reservationPrint(List<Map<String, Object>> reservation) {
		printLn(3);
		tap(3);
		System.out.println("◤ \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t◥");
		tap(3);
		System.out.println("예약 번호" + "\t" + "회원 아이디" + "\t" + "좌석 번호" + "\t" + "방문 일자" + "\t" + "\t" + "방문객수 " + "\t"
				+ "방문 시간" + "\t" + "예약자명" + "\t" + "연락처" + "\t" + "\t" + "예약자 이메일" + "\t" + "\t" + "요청사항");
		for (Map<String, Object> map : reservation) {
			tap(3);
			BigDecimal reserveNo = (BigDecimal) map.get("RESERVE_NO");
			String memID = (String) map.get("MEM_ID");
			BigDecimal seatNo = (BigDecimal) map.get("SEAT_NO");
			String reserveDate = (String) map.get("RESERVE_DATE");
			BigDecimal reserveNum = (BigDecimal) map.get("RESERVE_NUM");
			String reserveTime = (String) map.get("RESERVE_TIME");
			String reserveName = (String) map.get("RESERVE_NAME");
			String reserveTelno = (String) map.get("RESERVE_TELNO");
			String reserveEmail = (String) map.get("RESERVE_EMAIL");
			String reviewRequest = (String) map.get("RESERVE_REQUEST");

			System.out.println(reserveNo + "\t" + memID + "\t" + seatNo + "\t" + reserveDate + "\t" + reserveNum + "\t"
					+ reserveTime + "\t" + reserveName + "\t" + reserveTelno + "\t" + reserveEmail + "\t"
					+ reviewRequest);
		}
		tap(3);
		System.out.println("◣\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t◢");
		tap(3);
		printLn(3);
	}

//   리뷰 전체 조회
	public void reviewListPirnt(List<Map<String, Object>> reviewList) {
		printLn(3);
		tap(6);
		System.out.println("◤ \t\t\t\t\t\t\t\t              ◥");
		tap(6);
		System.out.println("번호 \t아이디 \t\t리뷰 제목 \t\t리뷰 내용 \t\t별점 \t 삭제여부");
		tap(6);
		System.out.println("-----------------------------------------------------------------------");
		for (Map<String, Object> map : reviewList) {
			tap(6);
			BigDecimal reviewNo = (BigDecimal) map.get("REVIEW_NO");
			String memId = (String) map.get("MEM_ID");
			String reviewTitle = (String) map.get("REVIEW_TITLE");
			String reviewContent = (String) map.get("REVIEW_CONTENT");
			BigDecimal reviewScore = (BigDecimal) map.get("REVIEW_SCORE");
			String reviewDelYN = (String) map.get("REVIEW_DELYN");
			System.out.println(reviewNo + "\t" + memId + "\t" + reviewTitle + "\t" + reviewContent + "\t" + reviewScore
					+ "\t" + reviewDelYN);
		}
		tap(6);
		System.out.println("◣\t\t\t\t\t\t\t\t              ◢");
		tap(6);
		printLn(3);
	}

//  메뉴 전체 조회
	public void menuListPirnt(List<Map<String, Object>> menuList) {
		printLn(3);
		tap(5);
		System.out.println("◤\t\t\t\t\t\t\t\t\t\t\t\t\t         ◥");
		tap(5);
		System.out.println("메뉴 번호" + "\t" + "메뉴 삭제" + "\t" + "메뉴 이름      " + "\t" + "메뉴 가격" + "\t\t\t\t" + "메뉴 설명");
		tap(5);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------");
		for (Map<String, Object> map : menuList) {
			tap(5);
			String menuNo = (String) map.get("MENU_NO");
			String menuDelYN = (String) map.get("MENU_DELYN");
			String menuName = (String) map.get("MENU_NAME");
			BigDecimal menuPrice = (BigDecimal) map.get("MENU_PRICE");
			String menuDetail = (String) map.get("MENU_DETAIL");
			System.out.println(menuNo + "\t" + menuDelYN + "\t" + menuName + "\t" + menuPrice + "\t" + menuDetail);
		}
		tap(5);
		System.out.println("◣\t\t\t\t\t\t\t\t\t\t\t\t\t               ◢");
		tap(5);
		printLn(3);
	}

//   공지사항 전체 조회
	public void noticeListPrint(List<Map<String, Object>> noticeList) {
		printLn(3);
		tap(6);
		System.out.println("◤ \t\t\t\t\t\t\t ◥");
		tap(6);
		System.out.println("번호" + "\t" + "공지 제목       " + "\t\t          " + "공지 내용");
		tap(6);
		System.out.println("----------------------------------------------------------");
		for (Map<String, Object> map : noticeList) {
			tap(6);
			BigDecimal ntNo = (BigDecimal) map.get("NT_NO");
			String ntTitle = (String) map.get("NT_TITLE");
			String ntContent = (String) map.get("NT_CONTENT");
			System.out.println(ntNo + "\t" + ntTitle + "\t" + ntContent);
		}
		tap(6);
		System.out.println("◣ \t\t\t\t\t\t\t ◢");
		tap(6);
		printLn(3);
	}

//   직원 전체 조회
	public void adminListPrint(List<Map<String, Object>> adminList) {
//      --  NAME / ID / PW / TELNO / BIR / DELYN
		tap(6);
		System.out.println("◤ \t\t\t\t\t\t\t\t ◥");
		tap(6);
		System.out.println("이름" + "\t" + "ID      " + "\t" + "PW    " + "\t" + "전화번호    " + "\t" + "생일       " + "\t"
				+ "    탈퇴 여부");
		tap(6);
		System.out.println("-----------------------------------------------------------------");
		for (Map<String, Object> map : adminList) {
			tap(6);
			String adminName = (String) map.get("ADMIN_NAME");
			String adminID2 = (String) map.get("ADMIN_ID");
			String adminPW = (String) map.get("ADMIN_PW");
			String adminTelno = (String) map.get("ADMIN_TELNO");
			String adminBir = (String) map.get("ADMIN_BIR");
			String adminDelyn = (String) map.get("ADMIN_DELYN");
			System.out.println(adminName + "\t" + adminID2 + "\t" + adminPW + "\t" + adminTelno + "\t" + adminBir + "\t"
					+ adminDelyn);
		}
		tap(6);
		System.out.println("◣ \t\t\t\t\t\t\t\t ◢");
		tap(6);
	}

//    회원 탈퇴 시 출력 화면
	public void memberListPirnt(List<Map<String, Object>> memberList) {
//      topVar(13);
		tap(4);
		System.out.println("◤\t\t\t\t\t\t\t\t\t\t\t\t\t◥");
		tap(4);
		System.out.println("성명" + "\t\t" + "아이디" + "\t\t" + "비밀번호" + "\t\t" + "이메일" + "\t\t" + "전화번호" + "\t\t" + "생일"
				+ "\t\t" + "삭제여부");
		tap(4);
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");
		for (Map<String, Object> map : memberList) {
			tap(4);
			String memName = (String) map.get("MEM_NAME");
			String memId = (String) map.get("MEM_ID");
			String memPw = (String) map.get("MEM_PW");
			String memEmail = (String) map.get("MEM_EMAIL");
			String memTel = (String) map.get("MEM_TELNO");
			String memBir = (String) map.get("MEM_BIR");
			String memDelyn = (String) map.get("MEM_DELYN");
			System.out.println(memName + "\t\t" + memId + "\t" + memPw + "\t" + memEmail + "\t" + memTel + "\t" + memBir
					+ "\t" + memDelyn);
		}
		tap(4);
		System.out.println("◣\t\t\t\t\t\t\t\t\t\t\t\t\t◢");
		bottomVar(13);
	}

	// 회원 리뷰
	public void updateReview() {
		tap(8);
		System.out.println("┌────────────────────────────┐");
		tap(9);
		System.out.println("1. 리뷰 제목 변경");
		tap(9);
		System.out.println("2. 리뷰 내용 변경");
		tap(9);
		System.out.println("3. 별점 변경");
		tap(9);
		System.out.println("4. 전체 변경");
		tap(8);
		System.out.println("└────────────────────────────┘");
		bottomVar(13);
	}

	// 회원 리뷰
	public void printReview() {
		tap(8);
		System.out.println("┌────────────────────────────┐");
		tap(9);
		System.out.println("0. 홈");
		tap(9);
		System.out.println("1. 리뷰 조회");
		tap(9);
		System.out.println("2. 리뷰 작성");
		tap(9);
		System.out.println("3. 리뷰 수정");
		tap(9);
		System.out.println("4. 리뷰 삭제");
		tap(8);
		System.out.println("└────────────────────────────┘");
		bottomVar(13);
	}

	// 관리자 메뉴 변경
	public void updateMenu() {
		tap(8);
		System.out.println("┌────────────────────────────┐");
		tap(9);
		System.out.println("1. 메뉴 이름 변경");
		tap(9);
		System.out.println("2. 메뉴 가격 변경");
		tap(9);
		System.out.println("3. 메뉴 설명 변경");
		tap(9);
		System.out.println("4. 메뉴 전체 변경");
		tap(8);
		System.out.println("└────────────────────────────┘");
		bottomVar(13);
	}

	public void printOrder() {
		printLn(3);
		tap(8);
		System.out.println("┌────────────────────────────┐");
		tap(9);
		System.out.println("1. 주문하기");
		tap(9);
		System.out.println("2. 공지사항 보기");
		tap(9);
		System.out.println("3. 리뷰 보기");
		tap(8);
		System.out.println("└────────────────────────────┘");
		bottomVar(13);
//      printLn(5);

	}

	public void printMenu() {
		tap(8);
		System.out.println("┌────────────────────────────┐");
		tap(9);
		System.out.println("1. 메뉴 전체 조회");
		tap(9);
		System.out.println("2. 메뉴 추가");
		tap(9);
		System.out.println("3. 메뉴 변경");
		tap(9);
		System.out.println("4. 메뉴 삭제");
		tap(9);
		System.out.println("5. 관리자 홈");
		tap(8);
		System.out.println("└────────────────────────────┘");
		bottomVar(13);
	}

	public void printAdminUpdate() {
		tap(8);
		System.out.println("┌────────────────────────────┐");
		tap(9);
		System.out.println("1. 이름 수정");
		tap(9);
		System.out.println("2. 아이디 수정");
		tap(9);
		System.out.println("3. 전화번호 수정");
		tap(9);
		System.out.println("4. 전체 수정");
		tap(8);
		System.out.println("└────────────────────────────┘");
		bottomVar(13);
	}

	
	// 관리자 메뉴
	public void printAdminHome() {
		printLn(3);
		topVar(13);
		tap(8);
		System.out.println("(👉ﾟヮﾟ)👉   관리자 관리   👈(ﾟヮﾟ👈)");
		tap(8);
		System.out.println("┌────────────────────────────┐");
		tap(9);
		System.out.println("1. 관리자 전체 조회");
		tap(9);
		System.out.println("2. 메뉴 관리");
		tap(9);
		System.out.println("3. 예약 관리");
		tap(8);
		System.out.println("└────────────────────────────┘");
		bottomVar(13);
	}

	// 0. 관리자 홈
	public void printAdminLogin() {
		printLn(3);
		topVar(13);
		tap(8);
		System.out.println("(👉ﾟヮﾟ)👉  관리자 홈페이지  👈(ﾟヮﾟ👈)");
		tap(8);
		System.out.println("┌────────────────────────────┐");
		tap(9);
		System.out.println("1. 관리자 로그인");
		tap(9);
		System.out.println("2. 관리자 아이디 찾기");
		tap(9);
		System.out.println("3. 관리자 비밀번호 찾기");
		tap(9);
		System.out.println("4. 관리자 정보 수정");
		tap(9);
		System.out.println("5. 관리자 탈퇴하기");
		tap(9);
		System.out.println("6. 관리자 회원가입");
		tap(8);
		System.out.println("└────────────────────────────┘");
		bottomVar(13);
	}

	
//	--- 멤버 ---
	// 멤버 예약 / 공지사항 / 리뷰
	public void printReserve() {
		printLn(3);
		topVar(13);
		tap(8);
		System.out.println("┌────────────────────────────┐");
		tap(9);
		System.out.println("1. 예약하기");
		tap(9);
		System.out.println("2. 공지사항 출력");
		tap(9);
		System.out.println("3. 리뷰 출력");
		tap(8);
		System.out.println("└────────────────────────────┘");
		bottomVar(13);
		printLn(2);
	}

	// 1-4. 멤버 정보 수정
	public void printLoginUpdate() {
		printLn(2);
		topVar(13);
		tap(7);
		System.out.println("~~~~~~~~~~~~~~~~ 정보 수정 ~~~~~~~~~~~~~~~~");
		tap(8);
		System.out.println("┌─────────────────────────┐");
		tap(9);
		System.out.println("✒️1. 아이디 수정");
		tap(9);
		System.out.println("✒️2. 이름 수정");
		tap(9);
		System.out.println("✒️3. 비밀번호 수정");
		tap(9);
		System.out.println("✒️4. 이메일 수정");
		tap(9);
		System.out.println("✒️5. 전화번호 수정");
		tap(9);
		System.out.println("✒️6. 전체 수정");
		tap(8);
		System.out.println("└─────────────────────────┘");
		bottomVar(13);
		printLn(2);
	}

	// 1. 멤버 홈
	public void printLogin() {
		printLn(3);
		topVar(13);
		tap(8);
		System.out.println("   ( ﾉ ﾟｰﾟ)ﾉ   회원 홈페이지   ＼(ﾟｰﾟ＼)");
		tap(8);
		System.out.println("┌───────────────────────────┐");
		tap(9);
		System.out.println("1. 로그인");
		tap(9);
		System.out.println("2. 아이디 찾기");
		tap(9);
		System.out.println("3. 비밀번호 찾기");
		tap(9);
		System.out.println("4. 회원정보 수정");
		tap(9);
		System.out.println("5. 회원 탈퇴");
		tap(8);
		System.out.println("└───────────────────────────┘");
		bottomVar(13);
		printLn(2);
	}

	// 0. 메인 홈
	public void printHome() {
		topVar(13);
		tap(9);
		System.out.println("🏦 HOME 🏦");
		tap(9);
		System.out.println("1. LOGIN");
		tap(9);
		System.out.println("2. SIGN UP");
		bottomVar(13);
	}

	// 로고
	public void printMain() {
		System.out.println("\r\n" + "\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠖⠒⠛⠛⠛⠓⠒⠲⠤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡀⢶⡈⢷⡈⢷⡀⠀⠀⠀⠀⠀⠀⠀⡰⠃⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⠈⣷⡈⢷⡈⢷⡀⠀⠀⠀⠀⠀⢰⡇⠀⠈⠀⠀⡀⠤⠀⠀⠠⠄⣀⠀⠀⠀⠀⠀⡘⠀⠀⠘⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣧⠘⣧⠘⣷⡈⢷⡀⠀⠀⠀⢀⣸⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠂⢄⠈⠀⠀⠀⠀⠸⡄⠀⠀⠀⢀⣤⣶⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣧⠘⣧⠘⣧⠘⣷⣀⣤⠾⠋⠁⣹⠂⠀⠀⠀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠇⠀⠀⢠⣿⣿⣿⣿⣿⣿⡟⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣧⠘⣧⣘⣧⣾⡟⠁⠀⣠⣶⣇⣤⠶⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣤⡀⠀⠀⠀⠱⠀⣠⠾⣦⡀⢀⣿⣿⣿⣿⣿⣿⣿⡇⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⢷⣿⣿⣿⣿⢃⣴⠟⠁⣼⣿⡏⠠⢭⣿⠿⣿⣿⣿⣿⣿⣿⣿⡿⢻⣷⣤⡀⠀⣿⣅⠀⠈⠻⣾⣿⣿⣿⣿⣿⣿⡟⣸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠟⠙⢿⣿⡟⠁⣠⠞⣿⣿⠀⣠⣤⡌⠀⠀⠀⠀⠈⠙⢛⣉⠁⠀⢻⣿⣿⡄⣟⠈⢷⡄⠀⢿⣿⣿⣿⣿⣿⢏⡼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠋⠀⢀⡾⢻⣷⡾⠁⠀⠹⣿⠰⢁⣹⡿⠀⠀⠀⠀⠀⡰⠛⣿⢷⡄⠈⣿⣿⣿⠟⣦⡀⠹⣆⢸⣿⣿⣿⣿⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠋⠀⢀⡾⢁⣰⠏⣤⡀⠀⢸⡏⡆⠈⠿⠟⠀⡀⠀⠀⠀⠀⢷⣿⡆⠁⢀⠟⠛⣏⠀⠈⢷⡀⠘⣷⣿⡿⢻⡅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠀⢀⡾⠁⣾⠹⣾⠁⣿⠀⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠘⢀⣿⡄⠀⠈⢷⣀⣾⣿⠁⠈⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠁⠀⣸⠃⢠⣿⡄⠙⠀⣿⡀⠀⠀⠻⣤⡀⠀⠈⠏⠉⠁⠌⠀⠀⠀⢀⣴⣿⣿⣿⡿⠁⠀⠀⠘⣿⡿⢻⡄⠀⢸⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⢀⡟⠀⣼⠱⡀⠁⠀⠈⠉⠳⢤⣀⡀⠙⠳⣦⣤⣄⠀⣤⣤⣶⣾⣿⡿⠿⠟⠋⠀⠀⠀⠀⠀⣼⣇⠘⣇⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⢸⡇⠀⣿⠳⢾⣶⣤⠆⠀⠀⠈⠉⠉⠉⠉⣉⣇⣉⣀⣉⠀⠙⠉⠳⢦⣀⠀⠀⠀⠀⠀⣴⡾⠋⠙⢷⣿⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⢸⡇⠀⡏⠀⠈⠛⢦⣄⣀⣀⣐⡀⠀⠀⠀⠻⠿⠛⠻⢿⡇⠀⠀⠀⠀⠈⠳⣄⠀⠀⠀⠉⠻⣦⣴⠟⣿⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⢸⣇⡼⢻⣆⡀⠀⠀⠀⠈⠉⠉⠉⢉⡏⠘⠃⠀⠀⠀⠰⠀⠀⢰⣦⡤⠀⠀⠈⡇⠀⠀⠀⠀⠙⡇⠀⣿⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⢾⣏⠀⠀⣙⣿⠆⠀⠀⠀⠀⠀⠀⢸⠡⡦⠀⠀⠀⢀⣄⠀⠀⣿⠋⠄⡀⠀⣠⠇⠀⠀⠀⠀⠀⡇⠀⣿⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⢸⡏⢷⣼⠏⠁⠀⠀⠀⠀⠀⠀⠀⣾⣤⣀⣠⣤⣀⣀⣁⣴⠁⠀⠀⢀⣤⠞⠋⠀⠀⠀⠀⠀⠀⡇⠀⣿⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣀⣸⡇⢀⡇⠀⠀⠀⠀⠀⠀⠀⠀⢠⡏⠙⣿⣿⣿⡟⠛⠛⠛⠴⣀⣠⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⣇⠀⣿⣀⣀⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣷⣤⣤⣤⣤⣤⣤⣤⣤⣾⣤⣤⣿⣯⣭⣤⣤⣤⣤⣤⣤⣼⣧⣤⣤⣤⣤⣤⣤⣤⣤⣴⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀       ⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠙⠛⠛⠛⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀         ⠛⠛⠛⠛⠃⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "					                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "	                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "	                                                            ");
	}
}