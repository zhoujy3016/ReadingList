package readingList.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import readingList.domain.SysDictEntity;

@Mapper
public interface SysDictMapper {
	List<SysDictEntity> findAllGroupByType();
}
