package com.bigdata2017.jdbc.bookshop.dao.test;

import java.util.List;

import com.bigdata2017.jdbc.bookshop.dao.AuthorDao;
import com.bigdata2017.jdbc.bookshop.vo.AuthorVo;

public class AuthorDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testDelete();

		testInsert();
		testGetList();
	}

	public static void testDelete() {
		new AuthorDao().delete();
	}

	public static void testInsert() {

		AuthorDao dao = new AuthorDao();
		AuthorVo vo = new AuthorVo();
		vo.setName("서원");
		dao.insert(vo);

		vo.setProfile("와이");
		dao.insert(vo);
		
		
		
		vo.setName("조정재");
		dao.insert(vo);

		vo.setProfile("...");
		dao.insert(vo);
		
		
		// new AuthorDao().insert(vo);
	}

	public static void testGetList() {
		// dao의getList 테스ㅡ

		AuthorDao dao = new AuthorDao();
		List<AuthorVo> list = dao.getList();
		for (AuthorVo vo : list) {
			System.out.println(vo);
		}

	}

}
