package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InserTest {
	// sql문을 미완성으로 가지고 java에서 짤때
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;

		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 자바는 원래는 동적으로 class로딩을 함->여기선 강제로
																// //com.bigdata2017.jdbc.MyDriver
																// 하는거
			// 왜 new OracleDriver 로 객체생성 안하는 forName으로 하는 이유:oracle에서 forName으로 하라고 스펙을 준것
			// com.bigdata2017.jdbc.MyDriver 예로 드라이버 형태 만들어본것

			// 2.connection 가져오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";// db마다 다름
			conn = DriverManager.getConnection(url, "dev", "dev");// MyDriver의 register된 register보고
			// 3.statement 준비: 완결되지 않은 sql문을 준비시켜야함
			String sql = "insert into author values(seq_author.nextval,?,?)";// ?,?-> 값이 바인딩 된다. 치환이 아님
			pstmt = conn.prepareStatement(sql);

			// 4.바인딩
			pstmt.setString(1, "공자");
			pstmt.setString(2, "어쩌구저쩌구");

			// 5.sql문 실행
			int count = pstmt.executeUpdate();
			System.out.println((count == 1 ? "성공" : "실패"));

			System.out.println("connection 성공!");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패 :" + e);
		} finally {
			// 자원 정리 ->역순으로 닫아줌

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {

				try {
					conn.close();
				}

				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}
