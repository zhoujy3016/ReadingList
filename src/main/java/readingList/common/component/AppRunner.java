package readingList.common.component;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import readingList.common.bean.DictCacheService;
import readingList.domain.Publish;
import readingList.domain.SysDictEntity;
import readingList.service.IPublishService;
import readingList.service.ISysDictService;
import readingList.service.PublishService;


@Component
public class AppRunner implements ApplicationRunner{

    @Autowired
    private ISysDictService sysDictService;
    
    @Autowired
    private DictCacheService dictCacheService;

    @Autowired
    private IPublishService publishService;
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 加载数据字典cache方式
		this.makeDictCache1();
		// 自建map方式
		this.makeDictCache2();
	}
	
	
	/**
	 * 将数据字典所有数据分类封装到dictConfigBean的map中
	 */
	private void makeDictCache1() {
		// 取得数据字典所有类型
		List<SysDictEntity> typeList = sysDictService.getSysDictEntityGroupByType();
		for(int i = 0; i < typeList.size(); i++) {
			String type = typeList.get(i).getType();
			// 根据类型查询每种数据字典，添加到map中
			sysDictService.getSysDictEntity(type);
		}

		// publish表cache
		List<Publish> publishList = publishService.findPublishList("pub");
	}
	
	
	private void makeDictCache2() {
		Map<String, Object> cacheMap = dictCacheService.getCacheMap(); 
		// 取得数据字典所有类型
		List<SysDictEntity> typeList = sysDictService.getSysDictEntityGroupByType();
		for(int i = 0; i < typeList.size(); i++) {
			String type = typeList.get(i).getType();
			// 根据类型查询每种数据字典，添加到map中
			List<SysDictEntity> dictList = sysDictService.getSysDictEntity(type);
			cacheMap.put(type, dictList);
		}
	}

}
