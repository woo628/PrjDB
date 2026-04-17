package db03;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Mem {
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbid = "sky";
	private static String dbpwd = "1234";
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		do { 
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
			
			MemDTO user = null;
			
			switch(choice) {
			case "1": 
				      
			case "2": System.out.println("조회할 아이디를 입력하세요"); 
					  String uid = sc.nextLine(); 
					  user = getTUser(uid);  
					  display(user); 
					  break;
					  
			case "3": user = inputData(); int aftcnt = addTUser(user); 
					  System.out.println(aftcnt + "건 저장되었습니다"); 
					  break;
					  
			case "4":
					  
			case "5": 
					  
			case "q": System.out.println("프로그램을 종료합니다"); System.exit(0); break;
			}	
		}while(true);
	}

	
	
	
		private static void display(MemDTO user) {
	}




		private static MemDTO getTUser(String uid) throws SQLException, ClassNotFoundException {
			Class.forName(driver);	
			Connection conn = DriverManager.getConnection(url, dbid, dbpwd);
			
			String sql = "select num,name,id,email,day from mem where ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,uid.toUpperCase());
			
			MemDTO user = null; 
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String id = rs.getString("id");
				String email = rs.getString("email");
				String pwd = rs.getString("pwd");
				String day = rs.getString("day");
				user = new MemDTO(num, name, id, email, pwd, day);
			} else {}
			pstmt.close();
			conn.close();
			return user;
	}

		private static int addTUser(MemDTO user) throws ClassNotFoundException, SQLException {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, dbid, dbpwd);
			
			String sql = "insert into mem (name,id,pwd,email) values (?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user.getName());
			pstmt.setString(2,user.getId());
			pstmt.setString(3,user.getPwd());
			pstmt.setString(4,user.getEmail());
			
			int aftcnt = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
			return aftcnt;
	}

	private static MemDTO inputData() {
		System.out.println("이름:");
		String username = sc.nextLine();
		System.out.println("아이디:");
		String userid = sc.nextLine();
		System.out.println("이메일:");
		String email = sc.nextLine();
		System.out.println("암호:");
		String pwd = sc.nextLine();
		
		MemDTO user = new MemDTO(0, username, userid, email, pwd, email);
		return user;
	}

}
