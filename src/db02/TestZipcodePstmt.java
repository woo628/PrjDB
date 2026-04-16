package db02;
import java.sql.*;
public class TestZipcodePstmt {
	// db 연결
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String id = "hr";
	private static String pwd = "1234";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pwd);
		// System.out.println(conn); oracle.jdbc.driver.T4CConnection@58134517
		
		String sql = "SELECT nvl(d.department_name,'부서없음') 부서명,\r\n"
				+ "e.first_name||' '||e.last_name 직원명,\r\n"
				+ "e.phone_number 직원전화\r\n"
				+ "from departments d\r\n"
				+ "right join employees e\r\n"
				+ "on d.department_id = e.department_id "
				+ "where d.department_id = ? ";
			//	+ "where d.department_id in (90,60,80)";
				
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		// 첫번째 ?의 값을 설정
		pstmt.setInt(1, 80);
		
		ResultSet rs = pstmt.executeQuery();
		
		String fmt = "%s, %s, %s";
		while (rs.next()) {
			String dname = rs.getString("부서명");
			String ename = rs.getString("직원명");
			String tel = rs.getString("직원전화");
			String msg = String.format(fmt,dname,ename,tel);
			System.out.println(msg);
		}
		
		
		
		
		rs.close();
		pstmt.close();
		conn.close();
	}

}
