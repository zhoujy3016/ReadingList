package readingList.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		logger.error(e.getMessage(), e);
		model.addAttribute("errorMsg", e.getMessage());
		return "error.html";
	}
}
