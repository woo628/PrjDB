package db03;
import java.sql.*;
import java.util.ArrayList;
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
// ---------------------------------------------------------------------------------------------------------			
			TUserDTO tuser = null;
			TUserDTO upuser = null;
			TUserDTO deuser = null;
			switch(choice) {
			case "1": ArrayList<TUserDTO> userList = getTUserList();
					  displaylist(userList);
				      break;
				      
			case "2": System.out.println("조회할 아이디를 입력하세요"); 
					  String uid = sc.nextLine(); 
					  tuser = getTUser(uid);  
					  // System.out.println(tuser.toString());
					  display(tuser); 
					  break;
					  
			case "3": tuser = inputData(); int aftcnt = addTUser(tuser); 
					  System.out.println(aftcnt + "건 저장되었습니다"); 
					  break;
					  
			case "4": System.out.println("수정할 아이디를 입력하세요");
			          String cid = sc.nextLine().toUpperCase();
			          tuser = getTUser(cid);
			          System.out.println("수정할 정보를 입력하세요");
					  upuser = updateData(); int upcnt = upTUser(upuser, cid);
					  System.out.println(upcnt + "건 수정되었습니다");
					  break;
					  
			case "5": System.out.println("삭제할 아이디를 입력하세요");
					  String did = sc.nextLine();
					  tuser = getTUser(did); 
					  int decnt = deTUser(deuser,did);
					  System.out.println(decnt + "건 삭제되었습니다");
					  break;
					  
			case "q": System.out.println("프로그램을 종료합니다"); System.exit(0); break;
			}
		} while(true); // 무한반복
	}



//---------------------------------------------------------------------------------------------------------------------------
	// 전체 목록 조회
	private static ArrayList<TUserDTO> getTUserList() throws ClassNotFoundException, SQLException {
		Class.forName(driver);	
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		String sql = "select * from TUSER order by id";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<TUserDTO> userList = new ArrayList<>();
		
		while (rs.next()) {
			String userid = rs.getString("id");
			String username = rs.getString("name");
			String email = rs.getString("email");
			
			TUserDTO tuser = new TUserDTO(userid,username,email);
			userList.add(tuser);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return userList;
	}
	
	// 전체 목록 출력
	private static void displaylist(ArrayList<TUserDTO> userList) {
		if (userList.size() == 0) {
			System.out.println("조회한 자료가없습니다");
			return;
		}
	
		String msg = "";
		for (TUserDTO tuser : userList) {
			String userid = tuser.getUserid();
			String username = tuser.getUsername();
			String email = tuser.getEmail();
			msg = """
				  %s %s %s
					""".formatted(userid,username,email);
				System.out.print(msg);
		}
		System.out.println("Press enter key.....");
		sc.nextLine();
	}

// ----------------------------------------------------------------------------------------------------------------
	// 입력받은 아이디로 한줄을 db 에서 조회
	private static TUserDTO getTUser(String uid) throws ClassNotFoundException, SQLException {
		Class.forName(driver);	
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		String sql = "select * from TUSER where ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,uid.toUpperCase());
		
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
		 System.out.println("Press enter key.....");
		 sc.nextLine();
	 }

// --------------------------------------------------------------------------------------------------------------------
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
// -----------------------------------------------------------------------------------
	// 수정 데이터 키보드로 입력받는다
	private static TUserDTO updateData() {
	
		System.out.println("이름:");
		String username = sc.nextLine();
		System.out.println("이메일:");
		String email = sc.nextLine();
		
		TUserDTO tuser = new TUserDTO(username, email);
		return tuser;
		}
	// 수정
	private static int upTUser(TUserDTO tuser, String cid) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		String sql = "update TUSER set name=?, email=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,tuser.getUsername());
		pstmt.setString(2,tuser.getEmail());
		pstmt.setString(3,cid);
		
		int upcnt = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return upcnt;
		}
// -------------------------------------------------------------------------------------
	// 삭제
	private static int deTUser(TUserDTO tuser, String did) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		String sql = "delete from TUSER where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,did);
		
		int decnt = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
			return decnt;
		}

}
