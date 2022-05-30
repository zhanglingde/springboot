package com.ling.springdatajpademo;

import com.ling.springdatajpademo.dao.BookDao;
import com.ling.springdatajpademo.model.Book;
import com.ling.springdatajpademo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class SpringDataJpaDemoApplicationTests {

	@Autowired
	BookDao bookDao;

	@Test
	void contextLoads() {
		Book book = new Book();
		book.setName("三国演义");
		book.setAuthor("罗贯中");
		bookDao.save(book);
	}

	@Test
	void test01(){
//		List<Book> list = bookDao.findAll();
//		for (Book book : list) {
//			System.out.println("book = " + book);
//		}

//		bookDao.deleteById(6L);

	}

	@Test
	void pageList(){
		PageRequest pageRequest = PageRequest.of(0,3, Sort.by(Sort.Order.desc("id")));
		Page<Book> page = bookDao.findAll(pageRequest);
		System.out.println();
		System.out.println("总记录数 = " + page.getTotalElements());
		System.out.println("总页数 = " + page.getTotalPages());
		System.out.println("查询到的数据 = " + page.getContent());
		System.out.println("每页的记录数 = " + page.getSize());
		System.out.println("是否还有下一页 = " + page.hasNext());
		System.out.println("是否还有上一页 = " + page.hasPrevious());
		System.out.println("是否是第一页 = " + page.isFirst());
		System.out.println("是否是最后一页 = " + page.isLast());
		System.out.println("当前页码 = " + page.getNumber());
		System.out.println("当前页的记录数 = " + page.getNumberOfElements());
	}

	@Test
	void test3(){
		List<Book> list = bookDao.getBookByAuthorEquals("鲁讯");
		for (Book book : list) {
			System.out.println("book = " + book);
		}
	}

	@Test
	void test4(){
		Book book = bookDao.maxIdBook();
		System.out.println("book = " + book);
	}

	@Autowired
	BookService bookService;

	@Test
	void test5(){
		bookService.updateBookById("哈哈",7L);
	}

}
