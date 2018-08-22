package readingList.common.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;
import readingList.common.bean.DictCacheService;

import java.util.HashMap;
import java.util.Map;

@Component
public class DictComponent {

    @Autowired
    private ConcurrentMapCacheManager cacheManager;

    @Autowired
    private DictCacheService dictCacheService;

    private static DictComponent dictComponent;

    public DictComponent() {
        dictComponent = this;
        dictComponent.cacheManager = this.cacheManager;
        dictComponent.dictCacheService = this.dictCacheService;
    }

    public static Map<String, Object> getDictCache(String types) {
        Map<String, Object> map = new HashMap<>();
        String[] arrType = types.split(",");
        Cache cache = dictComponent.cacheManager.getCache("dictionary");

        for(String type: arrType) {
            Cache.ValueWrapper vw = cache.get(type);
            map.put(type, vw.get());
        }

        return map;
    }

    public static Map<String, Object> getExtraDictCache(String type) {
        Map<String, Object> map = new HashMap<>();
        Cache cache = dictComponent.cacheManager.getCache("extra_dictionary");
        Cache.ValueWrapper vw = cache.get(type);
        map.put(type, vw.get());
        return map;
    }

    public static Map<String, Object>getDictMap(String types) {
        Map<String, Object> dictMap = dictComponent.dictCacheService.getCacheMap();
        Map<String, Object> map = new HashMap<>();
        String[] arrType = types.split(",");
        for(String type: arrType) {
            map.put(type, dictMap.get(type));
        }
        return map;
    }

}
