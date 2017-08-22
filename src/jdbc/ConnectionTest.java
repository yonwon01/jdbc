package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection conn=null;
		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 자바는 원래는 동적으로 class로딩을 함->여기선 강제로  //com.bigdata2017.jdbc.MyDriver
															// 하는거
			// 왜 new OracleDriver 로 객체생성 안하는 forName으로 하는 이유:oracle에서 forName으로 하라고 스펙을 준것
			// com.bigdata2017.jdbc.MyDriver 예로 드라이버 형태 만들어본것

			// 2.connection 가져오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";// db마다 다름

			conn=DriverManager.getConnection(url, "hr", "hr");// MyDriver의 register된 register보고

			System.out.println("connection 성공!");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패 :" + e);
		}finally {
			//자원 정리
			if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}

	}

}
