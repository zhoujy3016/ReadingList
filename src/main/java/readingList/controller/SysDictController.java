package readingList.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import readingList.common.component.DictComponent;


@RestController
public class SysDictController {

	@RequestMapping(value="/getDictCache/{types}")
	public Map<String, Object> getDictCache(@PathVariable("types") String types) throws Exception {
		Map<String, Object> map = DictComponent.getDictCache(types);
		
		return map;
	}
	
	@RequestMapping("/getDictMap/{types}")
	public Map<String, Object>getDictMap(@PathVariable("types") String types) {
		Map<String, Object> map = DictComponent.getDictMap(types);
		return map;
	}
}
