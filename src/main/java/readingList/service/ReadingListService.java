package readingList.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import readingList.common.annotation.DataFilter;
import readingList.domain.Book;
import readingList.domain.Publish;
import readingList.mapper.BookMapper;
import readingList.repository.ReadingListRepository;


@Service
public class ReadingListService implements IReadingListService {
	@Autowired 
	private ReadingListRepository readingListRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	@Override
	public List<Book> findAndSearch(Book book) {
		List<Book> result = readingListRepository.findAll(new Specification<Book>() {

			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
	            if (!StringUtils.isEmpty(book.getAuthor())) {
	                list.add(criteriaBuilder.like(root.get("author").as(String.class), "%" + book.getAuthor() + "%"));
	            }
	            
	            if (!StringUtils.isEmpty(book.getTitle())) {
	                list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + book.getTitle() + "%"));
	            }
	            Join<Book, Publish> join = root.join("publish", JoinType.LEFT);
	            Path<String> exp = join.get("publish_name");
	            
	            Predicate[] p = new Predicate[list.size()];
	            return criteriaBuilder.and(list.toArray(p));
			}

	    });
	    return result;
	}
	
	
	public int getCount() {
		int rowCount = this.jdbcTemplate.queryForObject("select count(*) from book", Integer.class);
		return rowCount;
	}


	@Override
	@DataFilter(tableAlias="b")
	public List<Book> findBookInfomation(Map<String, Object> params) {
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		if(bookMapper == mapper) {
			System.out.println("sqlSession取得mapper与自动注入mapper为同一对象");
		} else {
			System.out.println("sqlSession取得mapper与自动注入mapper为不同对象");
		}
		return mapper.findBookInfomation(params);
	}


	@Override
	public List<Book> findAll() {
		Sort sort = new Sort(Direction.DESC, "id");
		return this.readingListRepository.findAll(sort);
	}


	@Override
	public Book saveBook(Book book) {
		return this.readingListRepository.save(book);
	}


	@Override
	public List<Book> selectList(Book book) {
		List<Book> listBook = sqlSession.selectList("readingList.mapper.BookMapper.findBookInfomation", book);
		return listBook;
	}


	@Override
	@Cacheable(cacheNames="book")
	public List<Book> findBookInfomationLazy(Book book) {
		return this.bookMapper.findBookInfomationLazy(book.getAuthor(), book.getTitle());
	}


	@Override
	public int insertBook(Book book) {
		return bookMapper.saveBook(book);
	}


	@Override
	public void deleteBook(Long id) {
		this.bookMapper.deleteById(id);
	}


	@Override
	public Page<Book> findPage() {
		Page<Book> pageBook = this.readingListRepository.findAll(new PageRequest(1, 4));
		return pageBook;
	}

}
