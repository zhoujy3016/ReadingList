package readingList.mapper;

import java.util.List;


import readingList.domain.SysDictEntity;

public interface SysDictMapper {
	List<SysDictEntity> findAllGroupByType();
}
