package pl.exercise.spring.mvc.restful.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.exercise.spring.mvc.common.ErrorInfo;
import pl.exercise.spring.mvc.dto.DtoBase;

@RestController
public abstract class ApplicationApiRestControllerAbstract<T extends DtoBase> {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody	
	public ErrorInfo handleDefaultError(HttpServletRequest req, Exception ex) {
		return new ErrorInfo("500", req.getRequestURL().toString(), ex);
	}
	
	
	
}
