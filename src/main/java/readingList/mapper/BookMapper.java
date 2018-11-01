package readingList.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import readingList.domain.Book;


public interface BookMapper extends BaseMapper<Book> {

	/**
	 * 
	 * @return
	 */
	List<Book> findBookInfomation(@Param("params") Map<String, Object> params);
	
	
	List<Book> selectList(Book book);
	
	List<Book> findBookInfomationLazy(@Param("author")String author, @Param("title") String title);
	
	int saveBook(Book book);
	
	void deleteById(Long id);
}
