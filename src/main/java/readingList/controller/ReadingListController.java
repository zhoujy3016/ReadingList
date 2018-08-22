package readingList.controller;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import readingList.common.component.DictComponent;
import readingList.domain.Book;
import readingList.service.ReadingListService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;


@Controller
@RequestMapping("/readingList")
public class ReadingListController {
	
	private Log log = LogFactory.getLog(ReadingListController.class);
	
	@Autowired
	private ReadingListService readingListService;
	
	@GetMapping
	public String readerBooks(Book book, Model model) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("title", book.getTitle());
		params.put("author", book.getAuthor());
		List<Book> readingList = readingListService.findBookInfomation(params);
		
		
//		// 测试延迟加载
//		List<Book> listBook = readingListService.findBookInfomationLazy(book);
//		for(Book tempbook:listBook) {
//			if(tempbook.getPublish() != null) {
//				System.out.println("书名：" + tempbook.getTitle() + " 出版社名称：" + tempbook.getPublish().getPublish_name());	
//			}
//		}
		
		int count = readingListService.getCount();
//	    // 测试sqlsession 查询
//		List<Book> sessionList = readingListService.selectList(book);
				
		model.addAttribute("books", readingList);
		model.addAttribute("count", count);
		model.addAttribute("publishs", DictComponent.getExtraDictCache("pub"));
		log.info("测试一条log");
		return "reading/readingList";
	}
	
	@PostMapping(value="/add")
	public String addReaderInformation(@Validated Book book, BindingResult rs, Model model) {
		model.addAttribute("publishs", DictComponent.getExtraDictCache("pub"));

		// Validator 验证方式
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(book);
		if (!constraintViolations.isEmpty()) {
			Iterator iterator = constraintViolations.iterator();
			while(iterator.hasNext()) {
				ConstraintViolation<Object> constraint = (ConstraintViolation<Object>)iterator.next();
				System.out.println(constraint.getMessage());
			}
		}

		if(rs.hasErrors()){
	           return "reading/readingList";
	       }
		//this.readingListService.saveBook(book);
		this.readingListService.insertBook(book);
		return "redirect:/readingList";
	}
	
	@RequestMapping("/deleteBook/{bookId}")
	public String deleteBook(@PathVariable("bookId") Long id) {
		this.readingListService.deleteBook(id);
		return "redirect:/readingList";
	}
	
	
	@RequestMapping(value="/getData.json", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Book> jsonTest(Book book) {
		List<Book> readingList = this.readingListService.findAll();
		return readingList;
	}
	
	/**
	 * url传值方式:getBookUrlTitle?title=
	 * @param title
	 * @return
	 */
//	@RequestMapping(value="/getBookUrlTitle", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public List<Book> findBookByTitle(String title) {
//		List<Book> readingList = readingListService.findBookInfomation("", title, "");
//		return readingList;
//	}
	
	/**
	 * url传值方式:getBookUrlTitle?title= & author=
	 * @param book
	 * @return
	 */
//	@RequestMapping(value="/getBookUrlObject", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public List<Book> findBookByTitle(Book book) {
//		List<Book> readingList = readingListService.findBookInfomation(book.getAuthor(), book.getTitle(), "");
//		return readingList;
//	}
	
	
	@RequestMapping("/page")
	public @ResponseBody Page<Book> page() {
		Page<Book> pageBook = readingListService.findPage();
		return pageBook;
	}

}
