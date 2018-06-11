package readingList.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisely.spring_boot_starter_dictionary.DictCacheService;



@RestController
public class SysDictController {
	@Autowired
	private ConcurrentMapCacheManager cacheManager;
	
	@Autowired 
	private DictCacheService dictCacheService;
	
	@RequestMapping(value="/getDictCache/{types}")
	public Map<String, Object> getDictCache(@PathVariable("types") String types) throws Exception {
		Map<String, Object> map = new HashMap<>();
		String[] arrType = types.split(",");
		Cache cache = cacheManager.getCache("dictionary");
		
		for(String type: arrType) {
			ValueWrapper vw = cache.get(type);
			map.put(type, vw.get());
		}
		
		return map;
	}
	
	@RequestMapping("/getDictMap/{types}")
	public Map<String, Object>getDictMap(@PathVariable("types") String types) {
		Map<String, Object> dictMap = dictCacheService.getCacheMap();
		Map<String, Object> map = new HashMap<>();
		String[] arrType = types.split(",");
		for(String type: arrType) {
			map.put(type, dictMap.get(type));
		}
		return map;
	}
}
