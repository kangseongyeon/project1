package util;

import java.util.Scanner;

public class ScanUtil   {
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
	static Scanner sc = new Scanner(System.in);
	

	public static String nextLine(String print) {
		System.out.print(print);
		return nextLine();
	}
	
	// private -> 안내 문구 적어야 실행 가능
	// if) 안내문구 없이 실행하고 싶다면? => private 대신 public 사용
	private static String nextLine() {
		return sc.nextLine();
	}
	
	public static int reLogin() {
		return nextInt("재로그인 >> ");
	}
	
	public static int menu() {
		for (int i = 0; i < 10; i++) {
			System.out.print("\t");
		}
		return nextInt("MENU >> ");
	}
	
	
	public static int nextInt(String print) {
		// 안내 문구를 넣어줘야 실행 가능
		System.out.print(print);
		return nextInt();
	}
	
	// private : 사용 못함
	// why? 안내문구가 없으면 실행이 되지 않게 하기 위함 (기능을 막음)
	private static int nextInt() {
		while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("\t\t\t\t\t\t\t\t\t\t잘못 입력했습니니다.");
				System.out.print("\t\t\t\t\t\t\t\t\t\t다시 입력해주세요 :");
			}
		}
	}
}
