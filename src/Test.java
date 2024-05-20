import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import util.JDBCUtil;

public class Test {
	public static void main(String[] args) {
		Test test = new Test();
//		test.method1();
//		test.method2();
//		test.method3();
		test.method4();
	}
	
	Scanner sc = new Scanner(System.in);
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public void method1() {
//		파라미터 : String sql
//		DB의 데이터를 한 줄만 가져옴
//		selectOne (String sql)
		
//		DB의 데이터를 여러 줄 가져옴
//		selectList (String sql)
		
//		데이터 변경 (INSERT,UPDATE, DLELTE)
//		update (String sql)
		
		String sql = "SELECT * FROM MEMBER";
		List<Map<String, Object>> list = jdbc.selectList(sql);	// 여러 줄 가져옴
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	public void method2() {
		String sql = "SELECT * \r\n" + 
					 "FROM MEMBER\r\n" + 
					 "WHERE MEM_ID = 'a001'\r\n" + 
					 "AND MEM_PASS = 'asdfasdf'";
		
		// 여러 줄이 아니라 한 줄만 가져오기 때문에 굳이 list에 담을 필요 없이 map으로 가져오면 됨
		// String : 컬럼 이름 / Object : 실제 데이터 값
		Map<String, Object> map = jdbc.selectOne(sql); 
		System.out.println(map);
	}
	
	public void method3() {
		String sql = "SELECT * \r\n" + 
					 "FROM MEMBER\r\n" + 
					 "WHERE MEM_ID = ? \r\n" + 
					 "AND MEM_PASS = ?";	// 동적으로 데이터를 넣어주기 위해서는  데이터를 넣을 부분에 ?로 구멍을 뚫어줌 -> 데이터를 보내줌 (but, 데이터가 몇 개 올지 알 수 없음) -> 데이터를 list에 담아서 넘김
		
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("패스워드 : ");
		String pw = sc.next();
		
		// String말고 다른 데이터를 담을 수도 있기 때문에 String이 아닌 Object (모든 데이터를 담을 수 있게..)
		List<Object> param = new ArrayList();
		// ? 순서 -> if) ? 순서가 바꼈다면 add 위치도 바꿔줘야 함
		param.add(id);
		param.add(pw);
		
		Map<String, Object> map = jdbc.selectOne(sql, param);
		System.out.println(map);
	}
	
	public void method4() {
		String sql = "UPDATE MEMBER\r\n" + 
					 "SET MEM_PASS = '1234'\r\n" + 
					 "WHERE MEM_ID = 'a001'";
		
		// 리턴 값 : int -> 몇 개가 업테이트 되었는가?
		// int 0 -> 업데이트 실패
		jdbc.update(sql);
		
	}
}
