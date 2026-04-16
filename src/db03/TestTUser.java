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
			
			TUserDTO tuser = null;
			
			switch(choice) {
			case "1": searchDate(); break;
			case "2": System.out.println("조회할 아이디를 입력하세요"); 
					  String uid = sc.nextLine(); 
					  tuser = getTUser(uid);  
					  // System.out.println(tuser.toString());
					  display(tuser); 
					  break;
			case "3": tuser = inputData(); int aftcnt = addTUser(tuser); 
					  System.out.println(aftcnt + "건 저장되었습니다"); break;
			case "4":  break;
			case "5":  break;
			case "Q": System.out.println("프로그램을 종료합니다"); System.exit(0); break;
			}
			
		} while(true); // 무한반복
		
		
	}
	

	// 목록
	private static void searchDate() throws ClassNotFoundException, SQLException {
		Class.forName(driver);	
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		String sql = "select * from TUSER";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		String fmt = "%s, %s, %s";
		while (rs.next()) {
			String id = rs.getString("ID");
			String name = rs.getString("NAME");
			String email = rs.getString("EMAIL");
			String msg = String.format(fmt,id,name,email);
			System.out.println(msg);
		}
	}
	
	// 입력받은 아이디로 한줄을 db 에서 조회
	private static TUserDTO getTUser(String uid) throws ClassNotFoundException, SQLException {
		Class.forName(driver);	
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		String sql = "select * from TUSER where ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,uid);
		
		TUserDTO tuser = null; // 반드시 초기화필요
		
		ResultSet rs = pstmt.executeQuery();
		// primary key 라서 해당자료가 있다 없다 그래서 if문
		if(rs.next()) {
			String userid = rs.getString("id");
			String username = rs.getString("name");
			String email = rs.getString("email");		
			tuser = new TUserDTO(userid, username, email);
		} else {
			
		}
		pstmt.close();
		conn.close();
		return tuser;
	}
	
	// TUser 한줄을 출력한다
	 private static void display(TUserDTO tuser) {
		 if (tuser == null) {
			System.out.println("조회한 자료가 없습니다");
		} else {
			String msg = String.format("%s %s %s",tuser.getUserid(),tuser.getUsername(),tuser.getEmail());
			System.out.println(msg);
		}
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
