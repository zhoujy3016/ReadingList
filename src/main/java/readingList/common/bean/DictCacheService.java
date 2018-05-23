package readingList.common.bean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DictCacheService {
	
	private Map<String, Object> cacheMap;
	
	@PostConstruct
	public void init() {
		System.out.println("创建数据字典缓存bean, 并初始化map");
		cacheMap = new HashMap<>();
	}
	
	public DictCacheService() {
		super();
	}
	
	@PreDestroy
	public void destory() {
		System.out.println("销毁数据字典缓存bean");
	}

	public Map<String, Object> getCacheMap() {
		return cacheMap;
	}

	public void setCacheMap(Map<String, Object> cacheMap) {
		this.cacheMap = cacheMap;
	}

}
