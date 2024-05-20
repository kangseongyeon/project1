
package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import service.AdminService;
import service.MemberService;
import service.MenuService;
import service.OrderService;
import service.ReserveService;
import service.ReviewService;
import util.ScanUtil;
import util.View;
import view.Print;

public class MainController extends Print {
	Scanner sc = new Scanner(System.in);
	static public Map<String, Object> sessionStorage = new HashMap<>();
	MemberService memberService = MemberService.getInstance();
	AdminService adminService = AdminService.getInstance();
	ReviewService reviewService = ReviewService.getInstance();
	ReserveService reserveService = ReserveService.getInstance();
	MenuService menuService = MenuService.getInstance();
	OrderService orderService = OrderService.getInstance();

	boolean debug = false;

	public static void main(String[] args) {
		new MainController().start();
	}

	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case LOGIN:
				view = login();
				break;

			
			case MEMBER_LOGIN:
				view = memberLogin();
				break;
			case MEMBER_SIGN:
				view = memberSign();
				break;
			case MEMBER_DELETE:
				view = memberDelete();
				break;
			case MEMBER_FINDID:
				view = memberFindId();
				break;
			case MEMBER_FINDPW:
				view = memberFindPw();
				break;
			case MEMBER_UPDATE:
				view = memberUpdate();
				break;
            
			case RESERVE:
				view = reserve();
				break;
			case RESERVATION:
				view = reservation();
				break;
			case NOTICE:
				view = noticeList();
				break;

			case REVIEW_LIST:
				view = review_list();
				break;
			case REVIEW:
				view = review();
				break;
			case REVIEW_INSERT:
				view = review_insert();
				break;
			case REVIEW_UPDATE:
				view = review_update();
				break;
			case REVIEW_DELETE:
				view = review_delete();
				break;

				
			case ADMIN:
				view = admin();
				break;
			case ADMIN_DELETE:
				view = adminDelete();
				break;
			case ADMIN_LOGIN:
				view = adminLogin();
				break;
			case ADMIN_SIGN:
				view = adminSign();
				break;
			case ADMIN_FINDID:
				view = adminFindId();
				break;
			case ADMIN_FINDPW:
				view = adminFindPw();
				break;
			case ADMIN_UPDATE:
				view = adminUpdate();
				break;

				
			case ADMIN_HOME:
				view = adminHome();
				break;
			case ADMIN_LIST:
				view = adminList();
				break;
			case ADMIN_RESERVE:
				view = adminReserve();
				break;

			case MENU:
				view = menu();
				break;
			case MENU_LIST:
				view = menuList();
				break;
			case MENU_INSERT:
				view = menu_insert();
				break;
			case MENU_UPDATE:
				view = menu_update();
				break;
			case MENU_DELETE:
				view = menu_delete();
				break;
			case PAGING:
				view = paging();
				break;

			case ORDER:
				view = order();
				break;

			default:
				break;
			}
		}
	}

//   ------------------ 페이징 기법 ------------------

	private View paging() {
	    int page = 1;
	    if (sessionStorage.containsKey("page")) {
	        page = (int) sessionStorage.remove("page");
	    }
	    int startNo = 1 + (page - 1) * 8;
	    int endNo = page * 8;

	    List<Object> param = new ArrayList<>();
	    param.add(startNo);
	    param.add(endNo);

	    List<Map<String, Object>> list = menuService.menuPaging(param);
	    if ((list == null || list.isEmpty()) && page != 1) {
	        System.out.println("마지막 페이지입니다.");
	        page--;
	        sessionStorage.put("page", page);
	        return View.PAGING;
	    }

	    if (list != null) {
	    	menuListPirnt(list);
	    }
	    tap(8);
	    System.out.println("< 이전 페이지 \t 1 홈 \t 다음 페이지 >");
	    tap(8);
	    System.out.println("2 추가 \t 3 변경 \t 4 삭제 \t 0 주문");
	    tap(8);
	    String sel = ScanUtil.nextLine("페이지 : ");

	    if (sel.equals("<")) {
	        if (page != 1) {
	            page--;
	            sessionStorage.put("page", page);
	            return View.PAGING;
	        }
	    } else if (sel.equals(">")) {
	        page++;
	        startNo = 1 + (page - 1) * 8;
	        endNo = page * 8;
	        param.set(0, startNo);
	        param.set(1, endNo);
	        list = menuService.menuPaging(param);
	        if (list == null || list.isEmpty()) {
	        	tap(8);
	            System.out.println("마지막 페이지입니다.");
	            page--;
	            sessionStorage.put("page", page);
	        } else {
	            sessionStorage.put("page", page);
	            return View.PAGING;
	        }
	    } else if (sel.equals("1")) {
	        return View.MENU;
	    } else if (sel.equals("2")) {
	        return View.MENU_INSERT;
	    } else if (sel.equals("3")) {
	        return View.MENU_UPDATE;
	    } else if (sel.equals("4")) {
	        return View.MENU_DELETE;
	    } else if (sel.equals("0")) {
	        return View.ORDER;
	    }

	    sessionStorage.put("page", page);
	    return View.PAGING;
	}



// ------------------ 주문 ------------------

	private void printOrderList(int reserveNum) {
		List<Map<String, Object>> orderList = orderService.orderList(reserveNum);
		if (orderList.isEmpty()) {
			tap(9);
			System.out.println("주문 내역이 없습니다.");
		} else {
			for (Map<String, Object> order : orderList) {
				tap(9);
				System.out.println("메뉴 번호: " + order.get("MENU_NO") + ", 주문 수량: " + order.get("MENU_QTY"));
			}
			tap(9);
			System.out.println("====================");
		}
	}

	private View order() {
	       // paging();
	       View result;
	       do {
	           result = paging();
	           if (result == View.MENU) {
	               return View.MENU;
	           }
	       } while (result == View.PAGING);

	       if (debug)
	           System.out.println("-------- 주문 --------");

	       int reserveNum;
	       if (sessionStorage.containsKey("order")) {
	           reserveNum = (Integer) sessionStorage.get("order");
	       } else {
	           tap(9);
	           reserveNum = ScanUtil.nextInt("주문 번호 입력 : ");
	           sessionStorage.put("order", reserveNum);
	       }

	       while (true) {
	           List<Object> param = new ArrayList<>();
	           tap(9);
	           String orderName = ScanUtil.nextLine("메뉴 선택 : ");
	           tap(9);
	           int orderMenuNum = ScanUtil.nextInt("메뉴 수량 선택 : ");

	           param.add(reserveNum);
	           param.add(orderName);
	           param.add(orderMenuNum);

	           orderService.orderInsert(param);
	           tap(9);
	           System.out.println("메뉴가 장바구니에 담겼습니다.");
	           tap(9);
	           System.out.println("(👉ﾟヮﾟ)👉 주문 번호 : " + reserveNum 
	                   + ", 주문 메뉴 : " + orderName + ", 주문 수량 : " + orderMenuNum);
	           System.out.println();

	           if (!retry()) {
	               printOrderList(reserveNum);
	               tap(9);
	               System.out.println("주문이 완료됐습니다!");
	               sessionStorage.remove("order");
	               System.out.println();

	               String reTry;
	               while (true) {
	                   tap(9);
	                   System.out.print("결제를 진행하시겠습니까? (Y/N): ");
	                   reTry = sc.nextLine().trim().toLowerCase();
	                   if (reTry.equals("y")) {
	                       // 주문 금액 출력 및 결제 진행
	                       orderPayment(reserveNum);
	                       return reserve();
	                   } else if (reTry.equals("n")) {
	                       return View.ORDER;
	                   } else {
	                       tap(9);
	                       System.out.println("Y(y) 또는 N(N)을 입력해주세요.");
	                   }
	               }
	           }
	       }
	   }

	   private void orderPayment(int reserveNum) {
	       List<Map<String, Object>> orderPaymentInfo = orderService.orderPayment(reserveNum);
	       if (orderPaymentInfo.isEmpty()) {
	           System.out.println("주문 내역이 없습니다.");
	       } else {
	           int totalPrice = Integer.parseInt(orderPaymentInfo.get(0).get("TOTAL_PRICE").toString());
	           tap(9);
	           System.out.println("총 주문 금액: " + totalPrice + "원");
	           tap(9);
	           System.out.println(totalPrice + "원 결제가 완료되었습니다.");
	       }
	   }

//   ------------------ 리뷰 관리 ------------------

	// 4. 리뷰 삭제 : 한 줄 출력
	private View review_delete() {
		if (debug)
			System.out.println("--------- 리뷰 삭제 --------");

		review_list();
		tap(9);
		String memId = ScanUtil.nextLine("삭제할 리뷰 아이디 선택 : ");
		List<Object> param = new ArrayList();
		param.add(memId);

		int result = reviewService.reviewDelete(param);
		if (result == 0) {
			tap(9);
			System.out.println("삭제를 실패했습니다.");
			return View.REVIEW_DELETE;
		} else {
			System.out.println();
			tap(9);
			System.out.println("선택하신 " + memId + " 리뷰를 삭제했습니다.");
			System.out.println();

			List<Map<String, Object>> reviewInformation = reviewService.reviewInformation(param);
			for (Map<String, Object> map : reviewInformation) {
				reviewListPirnt(reviewInformation);
			}
			return View.REVIEW;
		}
	}

	// 3. 리뷰 수정
	private View review_update() {
		if (debug)
			System.out.println("--------- 리뷰 수정 --------");

		review_list();
		tap(9);
		String memId = ScanUtil.nextLine("변경할 리뷰 아이디 선택 : ");
		updateReview();

		int sel = ScanUtil.menu();

		List<Object> param = new ArrayList();
		if (sel == 1 || sel == 4) {
			tap(9);
			String title = ScanUtil.nextLine("제목 : ");
			param.add(title);
		} else if (sel == 2 || sel == 4) {
			tap(9);
			String contnet = ScanUtil.nextLine("내용 : ");
			param.add(contnet);
		} else if (sel == 3) {
			tap(9);
			int score = ScanUtil.nextInt("별점 : ");
			param.add(score);
		}
		param.add(memId);

		reviewService.reviewUpdate(param, sel);

		List<Object> param2 = new ArrayList<Object>();
		param2.add(memId);

		tap(9);
		System.out.println("리뷰가 수정되었습니다.");
		List<Map<String, Object>> reviewInformation = reviewService.reviewInformation(param2);
		for (Map<String, Object> map : reviewInformation) {
			reviewListPirnt(reviewInformation);
		}

		return View.REVIEW;
	}

// 2. 리뷰 추가 
	private View review_insert() {
		if (debug)
			System.out.println("-------- 리뷰 작성 --------");
		List<Object> param = new ArrayList();
		tap(9);
		String memId = ScanUtil.nextLine("아이디 : ");
		tap(9);
		String reviewTitle = ScanUtil.nextLine("리뷰 제목 : ");
		tap(9);
		String reviewContent = ScanUtil.nextLine("리뷰 내용 : ");
		tap(9);
		int reviewScore = ScanUtil.nextInt("별점 : ");

		param.add(memId);
		param.add(reviewTitle);
		param.add(reviewContent);
		param.add(reviewScore);

		reviewService.reviewInsert(param);
		tap(9);
		System.out.println("소중한 리뷰 감사합니다(´▽`ʃ♡ƪ)");
		tap(9);
		System.out.println(memId + "님 리뷰 제목 : " + reviewTitle + " 리뷰 내용 : " + reviewContent + " 별점 : " + reviewScore
				+ "이 등록되었습니다.");

		return View.REVIEW_LIST;

	}

	// 1. 리뷰 전체 조회 : 끝
	private View review_list() {
		List<Map<String, Object>> reviewList = reviewService.reviewList();
		reviewListPirnt(reviewList);
		return View.REVIEW;
	}

	// 0. 리뷰 홈
	private View review() {
		if (debug)
			System.out.println("============= 리뷰 =============");
		printReview();

		int sel = ScanUtil.menu();

		if (sel == 1) {
			if (debug)
				System.out.println("============= 1) 리뷰 조회 =============");
			return View.REVIEW_LIST;
		} else if (sel == 2) {
			if (debug)
				System.out.println("============= 2) 리뷰 작성 =============");
			return View.REVIEW_INSERT;
		} else if (sel == 3) {
			if (debug)
				System.out.println("============= 3) 리뷰 수정 =============");
			return View.REVIEW_UPDATE;
		} else if (sel == 4) {
			if (debug)
				System.out.println("============= 4) 리뷰 삭제 =============");
			return View.REVIEW_DELETE;
		} else if (sel == 0) {
			if (debug)
				System.out.println("============= 0) 로그아웃 =============");
			return View.RESERVE;
		}
		return View.HOME;
	}

//   ------------------ 메뉴 관리 ------------------
	// 4. 메뉴 삭제 : 끝
	private View menu_delete() {
		View result;
		do {
			result = paging();
//			result = menuList();
			if (result == View.MENU) {
				return View.MENU;
			}
		} while (result == View.PAGING);

		if (debug)
			System.out.println("--------- 메뉴 삭제 --------");

		List<Object> param = new ArrayList();
		tap(8);
		String menuNo = ScanUtil.nextLine("삭제하실 메뉴 번호를 입력해주세요 : ");
		param.add(menuNo);

		int result2 = menuService.menuDelete(param);
		if (result2 == 0) {
			tap(8);
			System.out.println("메뉴가 존재하지 않아 삭제를 실패했습니다.");
			return View.MENU_DELETE;
		} else {
			tap(6);

			List<Map<String, Object>> menuInformation = menuService.menuInformation(param);
			for (Map<String, Object> map : menuInformation) {
				System.out.println(map);
				tap(8);
				System.out.println("선택하신 " + menuNo + " 메뉴를 삭제했습니다.");
			}
		}
		printLn(3);
		return View.MENU;
	}

	// 3. 메뉴 변경 : 한 줄 출력 좀 더 예쁘게..!
	private View menu_update() {
	      View result;
	      do {
	         result = paging();
	         if (result == View.MENU) {
	            return View.MENU;
	         }
	      } while (result == View.PAGING);
	      
	      if (debug)
	         System.out.println("-------- 메뉴 변경 --------");
	      tap(8);
	      String menuNo = ScanUtil.nextLine("          변경할 메뉴 번호 선택 : ");
	      updateMenu();

	      int sel = ScanUtil.menu();
	      List<Object> param = new ArrayList();

	      if (sel == 1 || sel == 4) {
	         tap(8);
	         String menuName = ScanUtil.nextLine("이름 : ");
	         param.add(menuName);
	      } else if (sel == 2 || sel == 4) {
	         tap(8);
	         int mentPrice = ScanUtil.nextInt("가격 : ");
	         param.add(mentPrice);
	      } else if (sel == 3 || sel == 4) {
	         tap(8);
	         String menuDetail = ScanUtil.nextLine("설명 : ");
	         param.add(menuDetail);
	      }
	      param.add(menuNo);

	      menuService.menuUpdate(param, sel);

	      List<Object> param2 = new ArrayList<Object>();
	      param2.add(menuNo);

	      List<Map<String, Object>> detail = menuService.detail(param2);
	      tap(8);
	      for (Map<String, Object> map : detail) {
	         String menuno = (String)map.get("MENU_NO");
	         String menuName = (String)map.get("MENU_NAME");
	         int menuPrice = (int)map.get("MENU_NO");
	         String menuDetail = (String)map.get("MENU_DETAIL");
	         System.out.println(menuno+menuName+menuPrice+menuDetail);
	         System.out.println("선택하신 " + menuno + " 메뉴가 수정되었습니다.");
	      }
//	      List<Map<String, Object>> menuInformation = menuService.menuInformation(param2);
//	      for (Map<String, Object> map : menuInformation) {
//	         tap(3);
//	         System.out.println(map);
//	         tap(9);
//	         System.out.println("회원정보가 수정되었습니다.");
//	      }
//	      
//	      
	   
//	      List<Map<String, Object>> menuList = menuService.detail(param2);
//	      tap(8);
//	      for (Map<String, Object> map : detail) {
//	         String menuno = (String)map.get("MENU_NO");
//	         String menuName = (String)map.get("MENU_NAME");
//	         int menuPrice = (int)map.get("MENU_NO");
//	         String menuDetail = (String)map.get("MENU_DETAIL");
//	         System.out.println("선택하신 " + menuno + " 메뉴가 수정되었습니다.");
//	      }
	      printLn(3);
	      return View.MENU;
	   }

//	private View menuList() {
//		List<Map<String, Object>> menuList = menuService.menuList();
//		menuListPirnt(menuList);
//		return View.MENU;
//	}
	
	// 메뉴 추가
	private View menu_insert() {
		View result;
		do {
			result = paging();
//			result = menuList();
			if (result == View.MENU) {
				return View.MENU;
			}
		} while (result == View.PAGING);
	
		if (debug) {
			System.out.println("-------- 메뉴 추가 --------");
		}

		List<Object> param = new ArrayList<>();
		tap(8);
		String menuNo = ScanUtil.nextLine("추가할 메뉴 번호를 입력해주세요 : ");
		tap(8);
		String menuName = ScanUtil.nextLine("메뉴 이름을 입력해주세요 : ");
		tap(8);
		int menuPrice = ScanUtil.nextInt("메뉴 가격을 입력해주세요 : ");
		tap(8);
		String menuDetail = ScanUtil.nextLine("메뉴 설명을 입력해주세요 : ");

		param.add(menuNo);
		param.add(menuName);
		param.add(menuPrice);
		param.add(menuDetail);

		menuService.menuInsert(param);
		tap(6);
		System.out.println("==================================================================");
		tap(6);
		System.out.println("메뉴 번호: " + menuNo + "\t" + "메뉴 이름 : " + menuName + "\t" + "메뉴 가격 : " + menuPrice + "\t"
				+ "메뉴 설명 : " + menuDetail);
		printLn(3);
		return View.MENU;
	}

	// 1. 메뉴 전체 조회 : 끝
	private View menuList() {
//		List<Map<String, Object>> menuList = menuService.menuList();
//		menuListPirnt(menuList);
		View result;
		do {
			result = paging();
//			result = menuList();
			if (result == View.MENU) {
				return View.MENU;
			}
		} while (result == View.PAGING);
		
		return View.MENU;
	}

	private View menu() {
		if (debug)
			System.out.println("============= 메뉴 =============");
		
		printMenu();
		int sel = ScanUtil.menu();

		if (sel == 1) {
			tap(9);
			if (debug)
				System.out.println("============= 1) 메뉴 전체 조회 =============");
			return View.MENU_LIST;
//			return View.PAGING;
		} else if (sel == 2) {
			tap(9);
			if (debug)
				System.out.println("============= 2) 메뉴 추가 =============");
			return View.MENU_INSERT;
		} else if (sel == 3) {
			tap(9);
			if (debug)
				System.out.println("============= 3) 메뉴 변경 =============");
			return View.MENU_UPDATE;
		} else if (sel == 4) {
			tap(9);
			if (debug)
				System.out.println("============= 4) 메뉴 삭제 =============");
			return View.MENU_DELETE;
		} else if (sel == 5) {
			tap(9);
			if (debug)
				System.out.println("============= 5) 로그아웃 =============");
			return View.ADMIN_HOME;
		}
		return View.HOME;
	}

//   ------------------ 공지사항 ------------------
	// 공지사항 전체 출력 : 끝
	private View noticeList() {
		List<Map<String, Object>> noticeList = reserveService.noticeList();
		noticeListPrint(noticeList);
		return View.RESERVE;
	}

//   ------------------ 예약 관리 ------------------
	// 관리자 예약 리스트 출력 : 끝
	private View adminReserve() {
		List<Map<String, Object>> reserveList = reserveService.reservation();
		reservationPrint(reserveList);
		return View.ADMIN_HOME;
	}

// 예약 가능 메소드
	private void reservationTrue(int reserveNum, String reserveDate, String reserveTime) {
		List<Object> param = new ArrayList();

		String memid = (String) sessionStorage.get("member");

		String reserveName;
		String reserveTelNo;
		String reserveEmail;

		// 이름: 한글 2~5자
		while (true) {
			tap(8);
			reserveName = ScanUtil.nextLine("이름 : ");
			if (reserveName.matches("^[가-힣]{2,5}$")) {
				break;
			} else {
				tap(8);
				System.out.println("이름은 한글 2~5자여야 합니다.");
			}
		}

		// 이메일
		while (true) {
			tap(8);
			reserveEmail = ScanUtil.nextLine("이메일 : ");
			if (reserveEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
				break;
			} else {
				tap(8);
				System.out.println("유효하지 않은 이메일 형식입니다.");
			}
		}

		// 전화번호: 010으로 시작하고 숫자만 11자리
		while (true) {
			tap(8);
			reserveTelNo = ScanUtil.nextLine("전화번호 ('-' 입력) : ");
			if (reserveTelNo.matches("^\\d{3}-\\d{3,4}-\\d{4}$")) {
				break;
			} else {
				tap(8);
				System.out.println("유효하지 않은 전화번호 형식입니다. '-'를 포함해서 010으로 시작하는 11자리 숫자여야 합니다.");
			}
		}
		tap(8);
		String reserveRequest = ScanUtil.nextLine("요청사항 : ");

		param.add(reserveNum);
		param.add(reserveDate);
		param.add(reserveTime);
		param.add(memid);
		param.add(reserveName);
		param.add(reserveTelNo);
		param.add(reserveEmail);
		param.add(reserveRequest);

		reserveService.reserveInsert(param);
		tap(8);
		System.out.println("===================================");
		tap(8);
		System.out.println(reserveName + "님 " + reserveDate + "일 " + reserveTime + "에 예약이 완료되었습니다.");

		List<Map<String, Object>> resNo = reserveService.resNo();
		for (Map<String, Object> map : resNo) {
			tap(8);
			System.out.println("주문 번호 " + map + " 가 발급되었습니다.");
			tap(8);
			System.out.println("이제부터 주문을 할 수 있습니다.");
		}

	}

	// 예약 불가능 메소드
	private void reservationFalse(String reserveDate, String reserveTime) {
		List<Object> param = new ArrayList();
		param.add(reserveDate);
		param.add(reserveTime);
		tap(8);
		System.out.println(reserveDate + "일" + reserveTime + "에는 예약이 불가능합니다. 다른 날짜를 이용해주세요.");
	}

//   실제 예약 진행 코드 작성 필요
	private View reservation() {
		if (debug)
			System.out.println("============= 예약 =============");

		List<Object> param = new ArrayList();
		String memid = (String) sessionStorage.get("member");
		String reserveDate;
		String reserveTime;
		tap(8);
		int reserveNum = ScanUtil.nextInt("인원을 입력해주세요(최대 4명) : ");
		while (true) {
			if (reserveNum > 4) {
				tap(8);
				System.out.println("예약은 최대 4명만 가능합니다. 다시 예약해주세요.");
				return View.RESERVATION;
			} else {
				while (true) {
					tap(8);
					reserveDate = ScanUtil.nextLine("날짜를 입력해주세요(ex. 20240521) : ");
					if (reserveDate.matches("^\\d{4}\\d{2}\\d{2}$")) {
						break;
					} else {
						tap(8);
						System.out.println("유효하지 않은 날짜 형식입니다. yyyyMMdd 형식이어야 합니다.");
					}
				}

				while (true) {
					tap(8);
					reserveTime = ScanUtil.nextLine("시간을 입력해주세요(ex. 18:00) : ");
					if (reserveTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
						if (isValidTime(reserveTime)) {
							tap(8);
							System.out.println("예약 내역 : " + reserveDate + " / " + reserveTime);
							break;
						} else {
							tap(8);
							System.out.println("예약 시간은 11:00부터 14:00까지 또는 17:00부터 20:00까지 가능합니다.");
						}
					} else if (reserveTime.equalsIgnoreCase("exit")) {
						tap(8);
						System.out.println("종료.");
						break;
					} else {
						tap(8);
						System.out.println("유효하지 않은 형식입니다. 다시 시도해주세요");
					}
				}

				param.add(reserveNum);
				param.add(reserveDate);
				param.add(reserveTime);

				boolean reservation = reserveService.reservationSeat();
				if (reservation == false) {
					reservationFalse(reserveDate, reserveTime);
					return View.RESERVATION;
				} else if (reservation == true) {
					tap(8);
					System.out.print("예약이 가능합니다. 이대로 예약 하시겠습니까?(Y(y) / N(n)) : ");
					String reserve = sc.next();
					if (reserve.equals("Y") || reserve.equals("y")) {
						reservationTrue(reserveNum, reserveDate, reserveTime);
					} else if (reserve.equals("N") || reserve.equals("n")) {
						return View.RESERVE;
					} else {
						tap(8);
						System.out.println("Y 또는 N을 입력해주세요");
						// return 타입 재설정
					}

					printOrder();
					int sel = ScanUtil.menu();
					if (sel == 1) {
						tap(9);
						if (debug)
							System.out.println("1. 주문하기");
						return View.ORDER;
					} else if (sel == 2) {
						tap(9);
						if (debug)
							System.out.println("2. 공지사항 보기");
						return View.NOTICE;
					} else if (sel == 3) {
						tap(9);
						if (debug)
							System.out.println("3. 리뷰 보기");
						return View.REVIEW;
					}

					return View.ORDER;
				}
				break;
			}
		}
		return reserve();
	}

	// 예약 가능시간 설정
	private boolean isValidTime(String reserveTime) {
		String validTimeRegex = "^(1[1-3]:[0-5][0-9]|14:00|1[7-9]:[0-5][0-9]|20:00)$";
		return reserveTime.matches(validTimeRegex);
	}

	// 예약 관리 홈
	private View reserve() {
		printReserve();

		int sel = ScanUtil.menu();

		if (sel == 1) {
			if (debug)
				System.out.println("============= A) 예약하기 =============");
			return View.RESERVATION;
		} else if (sel == 2) {
			if (debug)
				System.out.println("============= B) 공지사항 =============");
			return View.NOTICE;
		} else if (sel == 3) {
			if (debug)
				System.out.println("============= C) 리뷰 =============");
			return View.REVIEW_LIST;
		} else if (sel == 0) {
			return View.HOME;
		}
		tap(9);
		printLn(1);
		System.out.println("1 ~ 3을 입력해주세요");
		return View.RESERVE;
	}

//   ------------------ 관리자 ------------------
	private View adminSign() {
		if (debug)
			System.out.println("============= 관리자 회원가입 =============");
		List<Object> param = new ArrayList();

		String adminId, adminName, adminTelNo, adminBir;
		while (true) {
			tap(8);
			System.out.println("아이디 \t (영문자로 시작하는 8~20자)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			adminId = ScanUtil.nextLine("");
			if (adminId.matches("^[a-zA-Z][a-zA-Z0-9]{7,19}$")) {
				break;
			} else {
				tap(8);
				System.out.println("아이디는 영문자로 시작하는 8~20자의 영문자 또는 숫자여야 합니다.");
			}
		}

		while (true) {
			tap(8);
			System.out.println("이름 \t (한글 2~5자)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			adminName = ScanUtil.nextLine(" ");
			if (adminName.matches("^[가-힣]{2,5}$")) {
				break;
			} else {
				tap(8);
				System.out.println("이름은 한글 2~5자여야 합니다.");
			}
		}

		while (true) {
			tap(8);
			System.out.println("전화번호 \t (형식 : 010-0000-0000)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			adminTelNo = ScanUtil.nextLine("");
			if (adminTelNo.matches("^\\d{3}-\\d{3,4}-\\d{4}$")) {
				break;
			} else {
				tap(8);
				System.out.println("유효하지 않은 전화번호 형식입니다. 010으로 시작하는 11자리 숫자여야 합니다.");
			}
		}

		while (true) {
			tap(8);
			System.out.println("생일 \t (형식 : yyyyMMdd)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			adminBir = ScanUtil.nextLine("");
			if (adminBir.matches("^\\d{4}\\d{2}\\d{2}$")) {
				break;
			} else {
				tap(8);
				System.out.println("유효하지 않은 생일 형식입니다. yyyyMMdd 형식이어야 합니다.");
			}
		}
		param.add(adminId);
		param.add(adminName);
		param.add(adminTelNo);
		param.add(adminBir);
		param.add(0);

		adminService.adminSign(param, 0);

		List<Map<String, Object>> list = adminService.adminPw();
		for (Map<String, Object> map : list) {
			printLn(2);
			tap(8);
			System.out.println("환영합니다!! ╰(*°▽°*)╯");
			tap(8);
			System.out.println("공통 비밀번호는" + map + "입니다.");
		}
		return View.ADMIN;
	}

	// 관리자 탈퇴 : 끝
	private View adminDelete() {
		if (debug)
			System.out.println("============= 관리자 탈퇴 =============");
		tap(8);
		System.out.println("✔️ 탈퇴를 하기 위해서는 본인인증이 필요합니다");

		List<Object> param = new ArrayList();
		tap(8);
		String adminId = ScanUtil.nextLine("아이디를 입력해주세요 : ");
		tap(8);
		String adminPw = ScanUtil.nextLine("비밀번호를 입력해주세요 : ");

		param.add(adminId);
		param.add(adminPw);

		int result = adminService.adminDelete(param);
		if (result == 0) {
			tap(8);
			System.out.println("관리자 정보가 없습니다.");
			return View.ADMIN_DELETE;
		} else {
			System.out.println();
			tap(8);
			System.out.println("삭제가 완료됐습니다.");
			tap(8);
			System.out.println("지금까지 함께 해주셔서 감사합니다 ( ﾟдﾟ)つ Bye");
			System.out.println();

//			List<Map<String, Object>> adminInformation = adminService.adminInformation(param);
//			for (Map<String, Object> map : adminInformation) {
//				adminListPrint(adminInformation);
//			}
		}
		return View.ADMIN;
	}

	// 	관리자 정보 수정 
	private View adminUpdate() {
		if (debug)
			System.out.println("--------- 관리자 정보 수정 --------");
		tap(8);
		String adminId = ScanUtil.nextLine("변경할 아이디 선택 : ");

		printAdminUpdate();

		int sel = ScanUtil.menu();

		List<Object> param = new ArrayList();
		if (sel == 1 || sel == 4) {
			tap(9);
			String adminName = ScanUtil.nextLine("이름 변경 : ");
			param.add(adminName);
		} else if (sel == 2 || sel == 4) {
			tap(9);
			String adminId2 = ScanUtil.nextLine("아이디 변경 : ");
//	         param.add(adminId);
			param.add(adminId2);
		} else if (sel == 3 || sel == 4) {
			tap(9);
			String adminTelno = ScanUtil.nextLine("전화번호 변경 : ");
			param.add(adminTelno);
		}
		param.add(adminId);

		adminService.adminUpdate(param, sel);

		List<Object> param2 = new ArrayList<Object>();
		param2.add(adminId);
		List<Map<String, Object>> adminInformation = adminService.adminInformation(param2);
		for (Map<String, Object> map : adminInformation) {
			tap(3);
			System.out.println(map);
			tap(9);
			System.out.println("회원정보가 수정되었습니다.");
		}
		return View.ADMIN;
	}

	// 1-3. 관리자 비밀번호 찾기 : 끝!
	private View adminFindPw() {
		if (debug)
			System.out.println("============= 3. 관리자 비밀번호 찾기 =============");
		List<Object> param = new ArrayList();
		tap(8);
		String adminName = ScanUtil.nextLine("이름 : ");
		tap(8);
		String adminID = ScanUtil.nextLine("아이디 : ");

		param.add(adminName);
		param.add(adminID);

		adminService.findAdminPw(param);
		List<Map<String, Object>> findAdminPw = adminService.findAdminPw(param);

		if (findAdminPw == null) {
			tap(8);
			System.out.println("정보가 없습니다.");
			return View.ADMIN_FINDPW;
		} else {
			for (Map<String, Object> map : findAdminPw) {
				tap(8);
				System.out.println("PW를 찾았습니다. ( •̀ ω •́ )✧");
				tap(8);
				System.out.println("비밀번호는 " + map + "입니다. ");
			}
			System.out.println();
			tap(8);
			System.out.println("재로그인");

			return View.ADMIN_LOGIN;
		}
	}

	// 1-2. 관리자 아이디 찾기
	private View adminFindId() {
		if (debug)
			System.out.println("============= 2. 관리자 아이디 찾기 =============");

		List<Object> param = new ArrayList();
		tap(8);
		String adminName = ScanUtil.nextLine("이름 : ");
		tap(8);
		String adminTelno = ScanUtil.nextLine("전화번호 ('-' 입력) :  ");

		param.add(adminName);
		param.add(adminTelno);

		adminService.findAdminId(param);
		List<Map<String, Object>> findAdminId = adminService.findAdminId(param);

		if (findAdminId == null) {
			tap(8);
			System.out.println("정보가 없습니다.");
			return View.ADMIN_FINDID;
		} else {
			for (Map<String, Object> map : findAdminId) {
				System.out.println();
				tap(8);
				System.out.println("ID를 찾았습니다. ( •̀ ω •́ )✧");
				tap(8);
				System.out.println("아이디는 " + map + "입니다. ");
			}
			System.out.println();
			tap(8);
			System.out.println("재로그인");
			return View.ADMIN_LOGIN;
		}
	}

	// 1-1. 관리자 로그인
	private View adminLogin() {
		if (debug)
			System.out.println("============= 관리자 로그인 =============");

		tap(8);
		String adminId = ScanUtil.nextLine("아이디 : ");
		tap(8);
		String adminPw = ScanUtil.nextLine("비밀번호 : ");

		List<Object> param = new ArrayList<Object>();
		param.add(adminId);
		param.add(adminPw);
		param.add(0);

		boolean loginChk = adminService.adminLogin(param, 0);
		if (!loginChk) {
			tap(8);
			System.out.println("로그인 실패");
			if (retry())
				return View.ADMIN_LOGIN;
			else
				return View.ADMIN;
		} else {
			sessionStorage.put("admin", adminId);
			String member = (String) sessionStorage.get("admin");
			tap(8);
			System.out.println(member + "님 환영합니다");
			tap(8);
			System.out.println("오늘도 파이팅~!!(oﾟvﾟ)ノ");

			return View.ADMIN_HOME;
		}
	}


	// 관리자 메인 홈
	private View adminHome() {
		if (debug)
			System.out.println("============= 관리자 메인 화면 =============");
		tap(8);
		printAdminHome();

		int sel = ScanUtil.menu();
		
		if (sel == 1) {
			if (debug)
				System.out.println("============= 1. 관리자 전체 조회 =============");
			return View.ADMIN_LIST;
		} else if (sel == 2) {
			if (debug)
				System.out.println("============= 2. 메뉴 관리 =============");
			return View.MENU;
		} else if (sel == 3) {
			if (debug)
				System.out.println("============= 3. 예약 관리 =============");
			return View.ADMIN_RESERVE;
		}
		return View.ADMIN;
	}

	// 관리자 전체 리스트 출력 : 끝
	private View adminList() {
		List<Map<String, Object>> adminList = adminService.adminList();
		adminListPrint(adminList);
		return View.ADMIN_HOME;
	}
	
	// 0. 관리자 홈
	private View admin() {
		if (debug)
			System.out.println("============= 관리자 =============");
		printAdminLogin();

		
		while (true) {
			int sel = ScanUtil.menu();

			if (sel >= 0 && sel <= 6) {
				if (sel == 0) {
					return View.HOME;
				} else if (sel == 1) {
					return View.ADMIN_LOGIN;
				} else if (sel == 2) {
					return View.ADMIN_FINDID;
				} else if (sel == 3) {
					return View.ADMIN_FINDPW;
				} else if (sel == 4) {
					return View.ADMIN_UPDATE;
				} else if (sel == 5) {
					return View.ADMIN_DELETE;
				} else if (sel == 6) {
					return View.ADMIN_SIGN;
				}
			} else {
				tap(8);	//
				System.out.println("0 ~ 6 사이의 숫자를 입력해주세요.");
				continue;
			}
		}
	}

//   ------------------ 멤버 ------------------

	// 1-5. 멤버 탈퇴 : 끝
	public View memberDelete() {
		tap(7);
		if (debug)
			System.out.println("~~~~~~~~~~~~~~~~ 탈퇴 ~~~~~~~~~~~~~~~~");
		tap(7);
		System.out.println("✔️ 탈퇴를 하기 위해서는 본인인증이 필요합니다");

		List<Object> param = new ArrayList();
		tap(7);
		String memberId = ScanUtil.nextLine("아이디를 입력해주세요 : ");
		tap(7);
		String memberPw = ScanUtil.nextLine("비밀번호를 입력해주세요 : ");

		param.add(memberId);
		param.add(memberPw);

		int result = memberService.memberDelete(param);
		if (result == 0) {
			tap(8);
			System.out.println("회원 정보가 없습니다.");
			return View.MEMBER_DELETE;
		} else {
			System.out.println();
			tap(7);
			System.out.println("탈퇴가 완료됐습니다.");
			tap(7);
			System.out.println("또 다시 만날 수 있죠..?  (´。＿。｀)");
			System.out.println();

			List<Map<String, Object>> myInformation = memberService.myInformation(param);
			for (Map<String, Object> map : myInformation) {
				memberListPirnt(myInformation);
			}
		}
		return View.HOME;
	}
	
	// 1-4. 정보 수정
	private View memberUpdate() {
		if (debug)
			System.out.println("~~~~~~~~~~~~~~~~ 정보 수정 ~~~~~~~~~~~~~~~~");

		tap(8);
		String memId = ScanUtil.nextLine("변경할 아이디 선택 : ");

		printLoginUpdate();

		int sel = ScanUtil.menu();

		List<Object> param = new ArrayList();
		if (sel == 1 || sel == 6) {
			tap(8);
			String memId2 = ScanUtil.nextLine("아이디 변경 : ");
//	         param.add(memId2);
			param.add(memId);
		} else if (sel == 2 || sel == 6) {
			tap(9);
			String memName = ScanUtil.nextLine("이름 변경 : ");
			param.add(memName);
		} else if (sel == 3 || sel == 6) {
			tap(8);
			String memPw = ScanUtil.nextLine("   비밀번호 변경 : ");
			param.add(memPw);
		} else if (sel == 4 || sel == 6) {
			tap(8);
			String memEmail = ScanUtil.nextLine("이메일 변경 : ");
			param.add(memEmail);
		} else if (sel == 5 || sel == 6) {
			tap(8);
			String memTelno = ScanUtil.nextLine("전화번호 변경 : ");
			param.add(memTelno);
		}
		param.add(memId);

		memberService.memberUpdate(param, sel);

		List<Object> param2 = new ArrayList<Object>();
		param2.add(memId);
		List<Map<String, Object>> memberInformation = memberService.memberInformation(param2);
		for (Map<String, Object> map : memberInformation) {
			System.out.println(map);
			tap(8);
			System.out.println("   회원정보가 수정되었습니다.");
		}

		return View.LOGIN;
	}

	// 1-3. 멤버 패스워드 찾기 : 끝~~
	private View memberFindPw() {
		if (debug)
			System.out.println("~~~~~~~~~~~~~~~~ 비밀번호 찾기 ~~~~~~~~~~~~~~~~");
		List<Object> param = new ArrayList();
		tap(8);
		String memID = ScanUtil.nextLine("아이디 : ");
		tap(8);
		String memEmail = ScanUtil.nextLine("이메일 : ");

		param.add(memID);
		param.add(memEmail);

		List<Map<String, Object>> findPw = memberService.findPw(param);

		if (findPw == null) {
			tap(8);
			System.out.println("정보가 없습니다");
			if (retry()) {
				return View.MEMBER_FINDPW;
			} else {
				return View.LOGIN;
			}
		} else {
			for (Map<String, Object> map : findPw) {
				tap(8);
				System.out.println("PW를 찾는 중... ( •̀ ω •́ )✧");
				tap(8);
				System.out.println("비밀번호는 " + map + "입니다. ");
			}
			System.out.println();
			tap(9);
			System.out.println("재로그인");
		}
		return View.MEMBER_LOGIN;
	}

	// 1-2. 멤버 아이디 찾기 : 끝~~
	private View memberFindId() {
		if (debug)
			System.out.println("~~~~~~~~~~~~~~~~ 아이디 찾기 ~~~~~~~~~~~~~~~~");

		List<Object> param = new ArrayList();
		tap(8); //
		String memName = ScanUtil.nextLine("이름 : ");
		tap(8); //
		String memEmail = ScanUtil.nextLine("이메일 : ");

		param.add(memName);
		param.add(memEmail);

		List<Map<String, Object>> findId = memberService.findId(param);
		if (findId == null) {
			tap(8); //
			System.out.println("정보가 없습니다");
			if (retry()) {
				return View.MEMBER_FINDID;
			} else {
				return View.LOGIN;
			}
		} else {
			for (Map<String, Object> map : findId) {
				tap(8); //
				System.out.println("ID찾는 중... ( •̀ ω •́ )✧");
				tap(8); //
				System.out.println("아이디는 " + map + "입니다. ");
			}
			System.out.println();
			tap(8);
			System.out.println("재로그인");
			return View.MEMBER_LOGIN;
		}
	}

	// 1-1. 멤버 로그인 : 끝~~	
	private View memberLogin() {
		tap(8); //
		String memId = ScanUtil.nextLine("아이디 : ");
		tap(8); //
		String memPw = ScanUtil.nextLine("비밀번호 : ");

		List<Object> param = new ArrayList<Object>();
		param.add(memId);
		param.add(memPw);
		param.add(1);

		boolean loginChk = memberService.login(param, 1);
		if (!loginChk) {
			tap(8); //
			System.out.println("로그인 실패");
			if (retry())
				return View.MEMBER_LOGIN;
			else
				return View.LOGIN;
		} else {
			sessionStorage.put("member", memId);
			String member = (String) sessionStorage.get("member");
			tap(8); //
			System.out.println("\"" + memId + "\" 님 환영합니다" + " （づ￣3￣）づ╭❤️～ ");
			return View.RESERVE;
		}
	}

	// 1. 멤버 홈 : 끝~~	
	private View login() {
//		tap(6);
//		if (debug)
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~ 로그인 ~~~~~~~~~~~~~~~~~~~~~~~~");
		printLogin();

		while (true) {
			int sel = ScanUtil.menu();

			if (sel >= 0 && sel <= 5) {
				if (sel == 0) {
					return View.HOME;
				} else if (sel == 1) {
					return View.MEMBER_LOGIN;
				} else if (sel == 2) {
					return View.MEMBER_FINDID;
				} else if (sel == 3) {
					return View.MEMBER_FINDPW;
				} else if (sel == 4) {
					return View.MEMBER_UPDATE;
				} else if (sel == 5) {
					return View.MEMBER_DELETE;
				}
			} else {
				tap(8);	//
				System.out.println("0 ~ 5 사이의 숫자를 입력해주세요.");
				continue;
			}
		}
	}

	// 2. 멤버 회원가입
	private View memberSign() {
//		tap(6);
//		if (debug)
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~ 회원가입 ~~~~~~~~~~~~~~~~~~~~~~~~");
		List<Object> param = new ArrayList();
		String id, name, pw, email, telNo, bir;

		while (true) {
			tap(8);
			System.out.println("아이디 \t (영문자로 시작하는 8~20자)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			id = ScanUtil.nextLine("");
			if (id.matches("^[a-zA-Z][a-zA-Z0-9]{7,19}$")) {
				break;
			} else {
				tap(7);
				System.out.println("아이디는 영문자로 시작하는 8~20자의 영문자 또는 숫자여야 합니다.");
			}
		}
		
		while (true) {
			tap(8);
			System.out.println("이름 \t (한글 2~5자)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			name = ScanUtil.nextLine("이름 : ");
			if (name.matches("^[가-힣]{2,5}$")) {
				break;
			} else {
				tap(8);
				System.out.println("이름은 한글 2~5자여야 합니다.");
			}
		}

		while (true) {
			tap(8);
			System.out.println("패스워드 \t (영문자 포함한 4글자 이상)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			pw = ScanUtil.nextLine("패스워드 : ");
			if (pw.matches("^(?=.*[a-z])(?=.*\\d).{4,20}$")) {
				break;
			} else {
				tap(8);
				System.out.println("4글자 이상의 영문 소문자, 숫자 포함해야 합니다.");
			}
		}

		while (true) {
			tap(8);
			System.out.println("이메일 \t (형식 : aaa@naver.com)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			email = ScanUtil.nextLine("이메일 : ");
			if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
				break;
			} else {
				tap(8);
				System.out.println("유효하지 않은 이메일 형식입니다.");
			}
		}

		while (true) {
			tap(8);
			System.out.println("전화번호 \t (형식 : 010-0000-0000)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			telNo = ScanUtil.nextLine("전화번호 : ");
			if (telNo.matches("^\\d{3}-\\d{3,4}-\\d{4}$")) {
				break;
			} else {
				tap(7);
				System.out.println("유효하지 않은 전화번호 형식입니다. (형식 : 010-0000-0000)");
			}
		}

		while (true) {
			tap(8);
			System.out.println("생일 \t (형식 : yyyyMMdd)");
			tap(8);
			System.out.print("(👉ﾟヮﾟ)👉 : ");
			bir = ScanUtil.nextLine("생일 : ");
			if (bir.matches("^\\d{4}\\d{2}\\d{2}$")) {
				break;
			} else {
				tap(7);
				System.out.println("유효하지 않은 생일 형식입니다. (형식 : yyyyMMdd)");
			}
		}

		param.add(id);
		param.add(1);
		param.add(name);
		param.add(pw);
		param.add(email);
		param.add(telNo);
		param.add(bir);

		memberService.memberSign(param, 1);
		System.out.println();
		tap(8);
		System.out.println("반갑습니다. ╰(*°▽°*)╯");
		tap(8);
		System.out.println(name + "님 회원가입이 완료됐습니다.");

		return View.LOGIN;
	}

	// Y/N 체크
	private boolean retry() {
		String reTry;
		while (true) {
			tap(8);
			System.out.print("다시 입력 하시겠습니까? (Y(y) / N(n)) : ");
			reTry = sc.nextLine().trim().toLowerCase();
			if (reTry.equals("y")) {
				return true;
			} else if (reTry.equals("n")) {
				return false;
			} else {
				tap(8);
				System.out.println("Y(y) 또는 N(N)을 입력해주세요.");
			}
		}
	}

	// 0. 메인 홈
	private View home() {
		printMain();

		printHome();

		int sel;
		while (true) {
			sel = ScanUtil.menu();
			if (sel >= 0 && sel <= 2) {
				break;
			} else {
				tap(8);	// 
				System.out.println("     0~2을 입력해주세요.");
			}
		}
		if (sel == 0)
			return View.ADMIN;
		else if (sel == 1)
			return View.LOGIN;
		else if (sel == 2)
			return View.MEMBER_SIGN;
		else {
			tap(7);	//
			System.out.println("해당하는 번호를 입력해주세요.");
			return View.HOME;
		}
	}

}