package pl.exercise.spring.mvc.restful.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.exercise.spring.mvc.dto.DtoBase;

public interface ApplicationApiRestController<T extends DtoBase> {

	@RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	T create(@RequestBody T dto) throws ParseException;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	T get(@PathVariable Long id);

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	T update(@PathVariable Long id, @RequestBody T type) throws ParseException;

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable Long id);
}
