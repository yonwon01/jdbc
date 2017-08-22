package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;

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
			// 3.statement 객체 생성
			stmt = conn.createStatement();

			// 4.sql문 실행
			String name = "도우넛";
			Long no = 11L;

			String sql = "update author" + "  set name='" + name + "'" + "  where no=" + no;// mybatis쓰면 쿼리를 xml에 넣어서
																							// 불편함을 덜 수 있다.

			// update는 몇개 업데이트하는지 return
			int count = stmt.executeUpdate(sql);// select 쿼리는 executeQuery 이고 insert,update,delete는 executeUpdate.

			// 5.성공유무
			System.out.println(count == 1 ? "성공" : "실패");

			System.out.println("connection 성공!");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패 :" + e);
		} finally {
			// 자원 정리 ->역순으로 닫아줌

			if (stmt != null) {
				try {
					stmt.close();
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
