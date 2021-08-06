package pl.exercise.spring.mvc.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.exercise.spring.mvc.assembler.AuthorAssembler;
import pl.exercise.spring.mvc.configuration.AppConf;
import pl.exercise.spring.mvc.dto.AuthorDto;
import pl.exercise.spring.mvc.service.AuthorService;

//TODO: implement interafce if needed
//@RestController
//@RequestMapping(AppConf.REST_API_VER + "/author")
public class AuthorRestController {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private AuthorAssembler authorAssembler;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<AuthorDto> author(
			@RequestParam(name = "page", defaultValue = AppConf.DEFAULT_PAGE_NUMBER, required = false) Integer page,
			@RequestParam(name = "size", defaultValue = AppConf.PAGE_SIZE, required = false) Integer size) {

		return this.authorAssembler.toDtos(this.authorService.findAll());
	}
}
