package readingList.mapper;

import org.apache.ibatis.annotations.Param;

import readingList.domain.SysUserEntity;


public interface UserMapper {
	SysUserEntity findByUserName(@Param("username") String username);
}
