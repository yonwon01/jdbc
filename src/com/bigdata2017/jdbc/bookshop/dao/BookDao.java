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
import com.bigdata2017.jdbc.bookshop.vo.BookVo;

public class BookDao {

	
	/////////////////////////Connection Method//////////////////////////
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

	///////////////////////getList////////////////////////////
	public List<BookVo> getList() {
		// 번호순으로 뽑아오기

		List<BookVo> list = new ArrayList<BookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			conn = getConnection();
			// 3.statement 객체 생성
			stmt = conn.createStatement();

			// 4.sql문 실행
			String sql = "select b.no, b.title, a.NAME, b.state from book b,author a where b.AUTHOR_NO=a.NO";// mybatis쓰면 쿼리를 xml에 넣어서 불편함을
																			// 덜 수 있다.
			rs = stmt.executeQuery(sql);// select 쿼리는 executeQuery 이고 insert,update,delete는 executeUpdate.

			// 5.결과 가져오기 (사용하기)
			while (rs.next()) {
				Long no = rs.getLong(1);// 책 번호
				String title = rs.getString(2);//책 제목
				String authorName = rs.getString(3);//작가이름
				String state=rs.getString(4);//상태
				//String authorNo=rs.getA
				BookVo vo = new BookVo();
			
				vo.setNo(no);
				vo.setTitle(title);
				vo.setAuthorName(authorName);
				vo.setState(state);

				list.add(vo);

			}

			//System.out.println("connection 성공!");

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

	public int insert(BookVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;// 변수가 있을떄preparee
		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			conn = getConnection();
			// 3.statement 객체 준비

			String sql = "insert into book values (seq_book.nextval,?,?,?)";// 값이 바인딩 되니까 ' '를쓰면 안되고 바로 ? 사용
			//String sql2 = "insert into author values (seq_author.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);

			// 4.binding
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getState());
			pstmt.setLong(3, vo.getAuthorNo());
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

	public int delete() {
		List<BookVo> list = new ArrayList<BookVo>();// 데이터 전부 다 가져오는 거
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
			String sql = "delete from book";// mybatis쓰면 쿼리를 xml에 넣어서 불편함을
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

	public int updateState(Long no, String state) {
		List<BookVo> list = new ArrayList<BookVo>();// 데이터 전부 다 가져오는 거
		Connection conn = null;
		int count=0;
		Statement stmt = null;
		ResultSet rs = null;
		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			conn = getConnection();
			// 3.statement 객체 생성
			stmt = conn.createStatement();

		// state애뎌중으로 바꿔야함 재고 잇음바꿩야해서
		
		stmt = conn.createStatement();

		// 4.sql문 실행
		String lent = "대여중";
		String sql=null;
		// no = 11L;
		 if(state.equals("대여가능")) {
		sql = "update book" + "  set state='" + lent + "'" + "  where no=" + no;// mybatis쓰면 쿼리를 xml에 넣어서
		 }													// 불편함을 덜 수 있다.

		// update는 몇개 업데이트하는지 return
		count = stmt.executeUpdate(sql);// select 쿼리는 executeQuery 이고 insert,update,delete는 executeUpdate.

		// 5.성공유무
		System.out.println(count == 1 ? "성공" : "실패");

		System.out.println("connection 성공!");

	}  catch (SQLException e) {
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
		return count;
	}
	}
}
