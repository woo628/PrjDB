package db01;
import java.sql.*;
public class TestHR {
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String id = "hr";
	private static String pwd = "1234";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		Statement stmt = conn.createStatement();
		String sql = "select a.employee_id, a.first_name, a.last_name, b.department_name, a.phone_number "
				+ "from employees a "
				+ "join departments b "
				+ "on a.department_id = b.department_id "
				+ "order by a.employee_id";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next() != false) {
			System.out.print(rs.getString(1) + ' ');
			System.out.print(rs.getString(2) + ' ');
			System.out.print(rs.getString(3) + ' ');
			System.out.print(rs.getString(4)+ ' ');
			System.out.print(rs.getString(5));
			System.out.println();
		}
		
		
		rs.close();
		stmt.close();
		conn.close();
	}

}
