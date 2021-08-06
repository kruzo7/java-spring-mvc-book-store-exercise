package pl.exercise.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.exercise.spring.mvc.common.ErrorInfo;

@Controller
public abstract class ApplicationControllerAbstract {

	@ExceptionHandler
	@ResponseBody
	ErrorInfo handleDefaultError(HttpServletRequest req, Exception ex) {
		return new ErrorInfo("500", req.getRequestURL().toString(), ex);
	}
}
