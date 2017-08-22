package com.bigdata2017.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2017.jdbc.bookshop.vo.AuthorVo;

public class AuthorDao {
	
	

	public int delete() {
		
		List<AuthorVo> list = new ArrayList<AuthorVo>();// 데이터 전부 다 가져오는 거
		Connection conn = null;
		int count=0;
		Statement stmt = null;
		ResultSet rs = null;
		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			conn = getConnection();
			// 3.statement 객체 생성
			stmt = conn.createStatement();

			// 4.sql문 실행
			String sql = "delete from author";// mybatis쓰면 쿼리를 xml에 넣어서 불편함을
																			// 덜 수 있다.
			 count = stmt.executeUpdate(sql);// select 쿼리는 executeQuery 이고 insert,update,delete는 executeUpdate.

			

			System.out.println("connection 성공!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패 :" + e);
		} finally {
			// 자원 정리 ->역순으로 닫아줌

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		return count;

	
		
		
		
		
	
	}
	public int delete (String name) {
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	// connection계속 하지않고 메쏘드로 만들어서 사용
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "dev", "dev");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	// insert메소드
	public int insert(AuthorVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;// 변수가 있을떄preparee
		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			conn = getConnection();
			// 3.statement 객체 준비

			String sql = "insert into author values (seq_author.nextval,?,?)";// 값이 바인딩 되니까 ' '를쓰면 안되고 바로 ? 사용

			pstmt = conn.prepareStatement(sql);

			// 4.binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getProfile());
			// mybatis쓰면 쿼리를 xml에 넣어서 불편함을

			// 5.실행// 덜 수 있다.
			count = pstmt.executeUpdate();// select 쿼리는 executeQuery 이고 insert,update,delete는 executeUpdate.

			System.out.println("connection 성공!");

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

		return count;

	}

	//////////////////////////////////////////////////////////////////////////////////////
	/* getList */
	public List<AuthorVo> getList() {

		List<AuthorVo> list = new ArrayList<AuthorVo>();// 데이터 전부 다 가져오는 거
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			conn = getConnection();
			// 3.statement 객체 생성
			stmt = conn.createStatement();

			// 4.sql문 실행
			String sql = "select no, name, profile from author order by no";// mybatis쓰면 쿼리를 xml에 넣어서 불편함을
																			// 덜 수 있다.
			rs = stmt.executeQuery(sql);// select 쿼리는 executeQuery 이고 insert,update,delete는 executeUpdate.

			// 5.결과 가져오기 (사용하기)
			while (rs.next()) {
				Long no = rs.getLong(1);// 첫번째 row의 값이 나옴
				String name = rs.getString(2);
				String profile = rs.getString(3);

				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setProfile(profile);

				list.add(vo);

			}

			System.out.println("connection 성공!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패 :" + e);
		} finally {
			// 자원 정리 ->역순으로 닫아줌

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		return list;

	}
}
