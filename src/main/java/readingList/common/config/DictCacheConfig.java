package readingList.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import readingList.common.bean.DictCacheService;
import readingList.common.conditional.WindowsCondition;

@Configuration
public class DictCacheConfig {

	@Bean
	@Conditional(WindowsCondition.class)
    DictCacheService dictCacheService() {
		return new DictCacheService();
	}
}
