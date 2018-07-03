package readingList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import readingList.domain.SysUserEntity;
import readingList.mapper.UserMapper;

public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUserEntity sysUserEndtity = this.userMapper.findByUserName(username);
		if(sysUserEndtity == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return sysUserEndtity;
	}

}
