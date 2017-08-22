package com.bigdata2017.jdbc.bookshop.dao.test;

import java.util.List;

import com.bigdata2017.jdbc.bookshop.dao.AuthorDao;
import com.bigdata2017.jdbc.bookshop.dao.BookDao;
import com.bigdata2017.jdbc.bookshop.vo.AuthorVo;
import com.bigdata2017.jdbc.bookshop.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
delete();
		insertTest();

		testGetList();
		
	}
	public static void delete() {
		BookDao dao=new BookDao();
		dao.delete();
	}
	public static void insertTest() {
		BookDao dao=new BookDao();
		
		BookVo vo =new BookVo();
		vo.setTitle("트와일라잇");
		
	 	vo.setAuthorNo(1L);
		vo.setState("대여가능");
		
		dao.insert(vo);
		
		vo.setTitle("뉴문");
		//vo.setAuthorName("스테파니메이어");
      vo.setAuthorNo(2L);
		//vo.setAuthorNo(2L);
		vo.setState("대여중");
		dao.insert(vo);
		
		
		
		
	}
	
	
	public static void testGetList() {
		// dao의getList 테스ㅡ

		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		for (BookVo vo : list) {
			System.out.println(vo);
		}

	}

}
