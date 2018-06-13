package readingList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import readingList.domain.SysDictEntity;

@Repository
public interface SysDictRepository  extends JpaRepository<SysDictEntity, Long>, JpaSpecificationExecutor<SysDictEntity>{
	
	List<SysDictEntity> findByType(String type);
}
