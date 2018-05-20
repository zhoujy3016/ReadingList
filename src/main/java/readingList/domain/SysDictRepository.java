package readingList.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDictRepository  extends JpaRepository<SysDictEntity, Long>, JpaSpecificationExecutor<SysDictEntity>{
	
	List<SysDictEntity> findByType(String type);
}
