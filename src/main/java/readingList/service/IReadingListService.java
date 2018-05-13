package readingList.service;

import java.util.List;

import readingList.domain.Book;



public interface IReadingListService {
	List<Book> findAndSearch(Book book);
	
	List<Book> findAll();
	
	Book saveBook(Book book);
}
