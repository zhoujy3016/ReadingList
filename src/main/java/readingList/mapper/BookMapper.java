package readingList.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import readingList.domain.Book;


public interface BookMapper {

	/**
	 * 
	 * @return
	 */
	List<Book> findBookInfomation(@Param("author")String author, @Param("title") String title);
	
	
	List<Book> selectList(Book book);
	
	List<Book> findBookInfomationLazy(@Param("author")String author, @Param("title") String title);
}
