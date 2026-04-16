package db03;
import java.sql.*;
import java.util.Scanner;
public class TestTUser {
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String id = "sky";
	private static String pwd = "1234";
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// CRUD 예제 (create,read,update,delete)
		do {
			// 화면출력
			System.out.println("========================");
			System.out.println("         회원정보       ");
			System.out.println("========================");
			System.out.println("1. 회원 목록");
			System.out.println("2. 회원 조회");
			System.out.println("3. 회원 추가");
			System.out.println("4. 회원 수정");
			System.out.println("5. 회원 삭제");
			System.out.println("Q. 종료");
			
			System.out.println("선택:");
			String choice = sc.nextLine();
			
			switch(choice) {
			case "1": break;
			case "2": break;
			case "3": TUserDTO tuser = inputData(); int aftcnt = addTUser(tuser); 
							System.out.println(aftcnt + "건 저장되었습니다"); break;
			case "4": break;
			case "5": break;
			case "Q": System.out.println("프로그램을 종료합니다"); System.exit(0); break;
			}
			
		} while(true); // 무한반복
		
		
		
		
	}
	// db insert
	private static int addTUser(TUserDTO tuser) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		String sql = "insert into TUSER values (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,tuser.getUserid());
		pstmt.setString(2,tuser.getUsername());
		pstmt.setString(3,tuser.getEmail());
		
		int aftcnt = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return aftcnt;
	}
	// 데이터 키보드로 입력받는다
	private static TUserDTO inputData() {
		System.out.println("아이디:");
		String userid = sc.nextLine();
		System.out.println("이름:");
		String username = sc.nextLine();
		System.out.println("이메일:");
		String email = sc.nextLine();
		
		TUserDTO tuser = new TUserDTO(userid, username, email);
		return tuser;
	}

}
