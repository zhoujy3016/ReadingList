package readingList.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SysDictController {
	@Autowired
	private ConcurrentMapCacheManager cacheManager;
	
	@RequestMapping(value="/getDictData/{types}")
	public Map<String, Object> getDictData(@PathVariable("types") String types) throws Exception {
		if(StringUtils.isEmpty(types)) {
			throw new Exception("type为空");
		}
		Map<String, Object> map = new HashMap<>();
		String[] arrType = types.split(",");
		Cache cache = cacheManager.getCache("dictionary");
		
		for(String type: arrType) {
			ValueWrapper vw = cache.get(type);
			map.put(type, vw.get());
		}
		
		return map;
	}
}
