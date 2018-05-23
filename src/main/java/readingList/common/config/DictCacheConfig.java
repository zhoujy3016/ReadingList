package readingList.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import readingList.common.bean.DictCacheService;

@Configuration
public class DictCacheConfig {
	
	@Bean
	DictCacheService dictCacheService() {
		return new DictCacheService();
	}
}
