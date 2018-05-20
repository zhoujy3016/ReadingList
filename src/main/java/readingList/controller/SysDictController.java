package readingList.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysDictController {
	
	@RequestMapping(value="/getDictData/{types}")
	public Map<String, Object> getDictData(@PathVariable("types") String types) {
		
		
		return null;
	}
}
