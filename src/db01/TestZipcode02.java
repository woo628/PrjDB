package db01;
import java.sql.*;
public class TestZipcode02 {
	// 연결 문자열 : connection string
		private static String driver = "oracle.jdbc.OracleDriver";
		private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
		private static String dbuid = "sky";
		private static String dbpwd = "1234";
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, dbuid, dbpwd);
		
		Statement stmt = conn.createStatement();
		String sql = "select zipcode, sido, gugun, dong, nvl(bunji,' ') bunj, seq from zipcode where dong like '%롯데백화점%'";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next() != false) {
			
			System.out.print(rs.getString(1) + ",");
			System.out.print(rs.getString(2)+ ",");
			System.out.print(rs.getString(3)+ ",");
			System.out.print(rs.getString(4)+ ",");
			System.out.print(rs.getString(5)+ ",");
			System.out.print(rs.getInt(6));
			System.out.println();
			
		 /* System.out.print(rs.getString("zipcode") + ",");
			System.out.print(rs.getString("sido")+ ",");
			System.out.print(rs.getString("gugun")+ ",");
			System.out.print(rs.getString("dong")+ ",");
			System.out.print(rs.getString("bunj")+ ",");
			System.out.print(rs.getInt("seq"));
			System.out.println(); */
		}
		
		rs.close();
		stmt.close();
		conn.close();
	}

}
