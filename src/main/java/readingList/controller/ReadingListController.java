package readingList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private ReadingListService readingListService;
	
	
	
	@GetMapping
	public String readerBooks(Book book, Model model) {
		List<Book> readingList = readingListService.findBookInfomation(book.getAuthor(), book.getTitle());
		
		int count = readingListService.getCount();
		
		model.addAttribute("books", readingList);
		model.addAttribute("count", count);
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
}
