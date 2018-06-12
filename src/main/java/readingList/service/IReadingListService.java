package readingList.service;

import java.util.List;

import org.springframework.data.domain.Page;

import readingList.domain.Book;



public interface IReadingListService {
	List<Book> findAndSearch(Book book);
	
	List<Book> findAll();
	
	Book saveBook(Book book);
	
	List<Book> findBookInfomationLazy(Book book);
	
	List<Book> findBookInfomation(String author, String title);
	
	List<Book> selectList(Book book);
	
	int insertBook(Book book);
	
	void deleteBook(Long id);
	
	Page<Book> findPage();
}
