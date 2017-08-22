package com.bigdata2017.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class MyDriver implements Driver {

	static {// driver들은 static안에서 초기화 시킨다.
		System.out.println("static code area");
		try {
			DriverManager.registerDriver(new MyDriver());// 자기자신을 등록함
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // driver등록
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		System.out.println(url);
		System.out.println(info);
		/* 연결 작업 */ // 디비마다 연결 작업이 다름
		return new MyConnection();
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

}
