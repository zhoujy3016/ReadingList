package readingList.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingListRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
	List<Book> findByReader(String reader);
	List<Book> findByTitle(String title);
	List<Book> findByTitleAndAuthor(String title, String author);
}
