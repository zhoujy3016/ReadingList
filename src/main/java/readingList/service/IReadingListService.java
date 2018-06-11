package readingList.service;

import java.util.List;

import readingList.domain.Book;



public interface IReadingListService {
	List<Book> findAndSearch(Book book);
	
	List<Book> findAll();
	
	Book saveBook(Book book);
	
	List<Book> findBookInfomationLazy(Book book);
	
	List<Book> findBookInfomation(String author, String title);
	
	List<Book> selectList(Book book);
	
	void insertBook(Book book);
}
