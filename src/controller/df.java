// ------------------ 주문 ------------------
//   private View order() {
//	   menuList();
//
//	   List<Object> param = new ArrayList();
//	   
//
//	   Scanner scanner = new Scanner(System.in);
//       Map<String, Integer> orders = new HashMap<>();
//
//       while (true) {
//           System.out.print("주문하고 싶은 메뉴 선택 : ");
//           String menuName = scanner.nextLine();
//           
//           System.out.print("주문 수량을 선택해주세요 : ");
//           while (!scanner.hasNextInt()) {
//               System.out.println("숫자를 입력해주세요.");
//               scanner.next(); 
//           }
//           int menuNum = scanner.nextInt();
//           scanner.nextLine(); 
//
//
//           orders.put(menuName, orders.getOrDefault(menuName, 0) + menuNum);
//
//           System.out.print("계속 주문을 진행하십니까? (Y/N) : ");
//           String continueOrder = scanner.nextLine();
//
//           if (continueOrder.equalsIgnoreCase("N")) {
//               break;
//           }
//       }
//       scanner.close();
//       
//       System.out.println("주문 내역:");
//       for (Map.Entry<String, Integer> entry : orders.entrySet()) {
//           System.out.println(entry.getKey() + " : " + entry.getValue() + "개");
//       }
//
//       scanner.close();
//	return View.MEMBER_LOGIN;
//   }