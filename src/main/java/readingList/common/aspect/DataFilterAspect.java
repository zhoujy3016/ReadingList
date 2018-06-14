package readingList.common.aspect;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import readingList.common.annotation.DataFilter;
import readingList.domain.SysUserEntity;

@Aspect
@Component
public class DataFilterAspect {
	
	@Pointcut("@annotation(readingList.common.annotation.DataFilter)")
	public void dataFilterCut() {
		
	}
	
	@Before("dataFilterCut()")
	public void dataFilter(JoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);
		Map params =  (Map) point.getArgs()[0];
		SysUserEntity principal = (SysUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String tableAlias = dataFilter.tableAlias();
		if(StringUtils.isNotBlank(tableAlias)) {
			tableAlias += ".";
		}
		String conditions = null;
		if("ROLE_USER".equals(principal.getRoleName())) {
			conditions = tableAlias + "creater= '" + principal.getUsername() + "' "; 
		}
		params.put("conditions", conditions);
	}
		
}
