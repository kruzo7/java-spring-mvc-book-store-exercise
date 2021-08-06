package pl.exercise.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import pl.exercise.spring.mvc.configuration.AppConf;
import pl.exercise.spring.mvc.dto.AuthorDto;
import pl.exercise.spring.mvc.dto.BookDto;

@Controller
@RequestMapping("/book")
public class BookController extends ApplicationControllerAbstract {
	
	@RequestMapping(path = "")
	public String findAll(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		BookDto[] books = restTemplate.getForObject(AppConf.REST_API + "/book", BookDto[].class);
		model.addAttribute("books", books);
		return "books";
	}

	//TODO: implement view
	@RequestMapping(path = "/{id}")
	public String findOne(@PathVariable Long id, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		BookDto book = restTemplate.getForObject(AppConf.REST_API + "/book/" + id.toString(), BookDto.class);
		model.addAttribute("book", book);
		return "book";
	}
	
	@RequestMapping(path = "/{id}/add")
	public String addOne(@PathVariable Long id, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		AuthorDto[] authors = restTemplate.getForObject(AppConf.REST_API + "/author", AuthorDto[].class);
		model.addAttribute("authors", authors);
		return "book";
	}

}
