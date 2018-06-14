package readingList.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import readingList.domain.Book;



public interface IReadingListService {
	List<Book> findAndSearch(Book book);
	
	List<Book> findAll();
	
	Book saveBook(Book book);
	
	List<Book> findBookInfomationLazy(Book book);
	
	List<Book> findBookInfomation(Map<String, String> params);
	
	List<Book> selectList(Book book);
	
	int insertBook(Book book);
	
	void deleteBook(Long id);
	
	Page<Book> findPage();
}
