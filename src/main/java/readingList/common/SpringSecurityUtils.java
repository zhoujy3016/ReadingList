package readingList.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import readingList.domain.SysUserEntity;

/**
 * SpringSecurity工具类
 */
public class SpringSecurityUtils {
    public static SysUserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUserEntity userEntity = (SysUserEntity) authentication.getPrincipal();
        return userEntity;
    }
}
