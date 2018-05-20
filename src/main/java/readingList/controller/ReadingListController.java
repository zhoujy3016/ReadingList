package readingList.controller;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import readingList.domain.Book;
import readingList.service.ReadingListService;


@Controller
@RequestMapping("/readingList")
public class ReadingListController {
	
	private Log log = LogFactory.getLog(ReadingListController.class);
	
	@Autowired
	private ReadingListService readingListService;
	
	@Autowired
	private ConcurrentMapCacheManager cacheManager;
	
	@GetMapping
	public String readerBooks(Book book, Model model) {
		List<Book> readingList = readingListService.findBookInfomation(book.getAuthor(), book.getTitle());
		
		
		// 测试延迟加载
		List<Book> listBook = readingListService.findBookInfomationLazy(book);
		this.printCacheData();
		for(Book tempbook:listBook) {
			if(tempbook.getPublish() != null) {
				System.out.println("书名：" + tempbook.getTitle() + " 出版社名称：" + tempbook.getPublish().getPublish_name());	
			}
		}
		
		int count = readingListService.getCount();
//		// 测试sqlsession 查询
		List<Book> sessionList = readingListService.selectList(book);
				
		model.addAttribute("books", readingList);
		model.addAttribute("count", count);
		log.info("测试一条log");
		return "reading/readingList";
	}
	
	@PostMapping(value="/add")
	public String addReaderInformation(@Validated Book book, BindingResult rs) {
		if(rs.hasErrors()){
	           return "reading/readingList";
	       }
		this.readingListService.saveBook(book);
		return "redirect:/readingList";
	}
	
	@RequestMapping(value="/getData.json", produces="text/json;charset=UTF-8")
	@ResponseBody
	public List<Book> jsonTest(Book book) {
		List<Book> readingList = this.readingListService.findAll();
		
		JSONObject json = new JSONObject();
		json.put("books", readingList);
		return readingList;
	}
	
	private void printCacheData() {
		Object[] arr = cacheManager.getCacheNames().toArray();
		for(int i=0; i < arr.length; i++) {
			String cacheName = arr[i].toString();
			Cache cache = cacheManager.getCache(cacheName);
			System.out.print("1");
		}
	}

}
