package pl.exercise.spring.mvc.service;

import java.text.ParseException;
import java.util.List;

import pl.exercise.spring.mvc.dto.BookDto;

public interface BookService {

	List<BookDto> findAll(Integer page, Integer size);

	List<BookDto> findAll();

	BookDto findOne(Long id);

	void delete(Long id);	

	BookDto findOneEager(Long id);

	BookDto borrowBook(Long bookId, Long borrowerId);

	BookDto returnBook(Long bookId, Long borrowerId);

	List<BookDto> search(String title, String isbn, String firstName, String lastName, List<String> categories,
			Integer page, Integer size);

	List<String> getBookCoverTypes();

	List<String> getBookCategoryTypes();

	BookDto create(BookDto bookDto) throws ParseException;

	BookDto update(BookDto bookDto) throws ParseException;

}
