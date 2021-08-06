package pl.exercise.spring.mvc.restful.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.exercise.spring.mvc.configuration.AppConf;
import pl.exercise.spring.mvc.dto.BookDto;
import pl.exercise.spring.mvc.service.BookService;

@RestController
@RequestMapping(AppConf.REST_API_VER + "/book")
public class BookRestController extends ApplicationApiRestControllerAbstract<BookDto>
		implements ApplicationApiRestController<BookDto> {

	@Autowired
	private BookService bookService;

	// TODO: QueryDSL vs. JPQL - like search adding % at end and begin to each
	// parameter or use in @Query
	// @RequestMapping(method = RequestMethod.GET, produces = "application/json;
	// charset=UTF-8")
	// public List<BookDto> search(@RequestParam(name = "title", required =
	// false) String title,
	// @RequestParam(name = "isbn", required = false) String isbn,
	// @RequestParam(name = "authorFirstName", required = false) String
	// authorFirstName,
	// @RequestParam(name = "authorLastName", required = false) String
	// authorLastName,
	// @RequestParam(name = "categories", required = false) List<String>
	// categories,
	// @RequestParam(name = "page", defaultValue = AppConf.DEFAULT_PAGE_NUMBER,
	// required = false) Integer page,
	// @RequestParam(name = "size", defaultValue = AppConf.PAGE_SIZE, required =
	// false) Integer size) {
	// return this.bookAssembler.toDtos(this.bookService.search(title, isbn,
	// authorFirstName, authorLastName,
	// this.bookAssembler.toEnumForQuery(categories), page, size));
	// }

	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<BookDto> search(@RequestParam(name = "title", defaultValue = "%", required = false) String title,
			@RequestParam(name = "isbn", defaultValue = "%", required = false) String isbn,
			@RequestParam(name = "authorFirstName", defaultValue = "%", required = false) String authorFirstName,
			@RequestParam(name = "authorLastName", defaultValue = "%", required = false) String authorLastName,
			@RequestParam(name = "categories", required = false) List<String> categories,
			@RequestParam(name = "page", defaultValue = AppConf.DEFAULT_PAGE_NUMBER, required = false) Integer page,
			@RequestParam(name = "size", defaultValue = AppConf.PAGE_SIZE, required = false) Integer size) {
		return this.bookService.search(title, isbn, authorFirstName, authorLastName, categories, page, size);
	}

	public BookDto create(@RequestBody BookDto bookDto) throws ParseException {
		return this.bookService.create(bookDto);
	}

	public BookDto get(@PathVariable Long id) {
		return this.bookService.findOneEager(id);
	}

	public BookDto update(@PathVariable Long id, @RequestBody BookDto bookDto) throws ParseException {
		return this.bookService.update(bookDto);
	}

	public void delete(@PathVariable("id") Long id) {
		this.bookService.delete(id);
	}

	@RequestMapping(path = "/{bookId}/borrowBook/{borrowerId}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public BookDto borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
		return this.bookService.borrowBook(bookId, borrowerId);
	}

	@RequestMapping(path = "/{bookId}/returnBook/{borrowerId}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public BookDto returnBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
		return this.bookService.returnBook(bookId, borrowerId);
	}

	@RequestMapping(path = "/{bookId}/bookCoverTypes", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<String> getBookCoverTypes(@PathVariable Long bookId) {
		return this.bookService.getBookCoverTypes();
	}

	@RequestMapping(path = "/{bookId}/bookCategoryTypes", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<String> getBookCategoryTypes(@PathVariable Long bookId) {
		return this.bookService.getBookCategoryTypes();
	}

}
