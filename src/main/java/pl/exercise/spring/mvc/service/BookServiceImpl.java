package pl.exercise.spring.mvc.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pl.exercise.spring.mvc.assembler.AuthorAssembler;
import pl.exercise.spring.mvc.assembler.BookAssembler;
import pl.exercise.spring.mvc.assembler.BorrowerAssembler;
import pl.exercise.spring.mvc.common.ApplicationCustomException;
import pl.exercise.spring.mvc.configuration.AppConf;
import pl.exercise.spring.mvc.dto.BookDto;
import pl.exercise.spring.mvc.entity.AuthorEntity;
import pl.exercise.spring.mvc.entity.BookEntity;
import pl.exercise.spring.mvc.entity.BorrowDetailEntity;
import pl.exercise.spring.mvc.entity.BorrowerEntity;
import pl.exercise.spring.mvc.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BorrowerService borrowerService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookAssembler bookAssembler;

	@Autowired
	private BorrowerAssembler borrowerAssembler;

	@Autowired
	private AuthorAssembler authorAssembler;

	public List<BookDto> findAll(Integer page, Integer size) {
		return this.bookAssembler
				.toDtos(this.bookRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"))));
	}

	public List<BookDto> findAll() {
		return this.bookAssembler.toDtos(this.bookRepository.findAll());
	}

	public BookDto findOne(Long id) {
		return this.bookAssembler.toDto(this.bookRepository.findOne(id));
	}

	public BookDto findOneEager(Long id) {
		BookEntity book = this.bookRepository.getOne(id);
		BookDto bookDto = this.bookAssembler.toDto(book);
		setBorrower(bookDto, book);
		return bookDto;
	}

	public void delete(Long id) {
		this.bookRepository.delete(id);
	}

	// TODO: QueryDSL vs. JPQL - like search adding % at end and begin to each
	// parameter or use in @Query
	// public Page<BookEntity> search(String title, String isbn, String
	// firstName, String lastName,
	// List<BookCategoryType> categories, Integer page, Integer size) {
	// return
	// this.bookRepository.findDistinctBookEntityByTitleIsLikeAndIsbnIsLikeAndAuthor_FirstNameIsLikeAndAuthor_LastNameIsLikeAndCategoriesIsIn(title,
	// isbn, firstName, lastName, categories,
	// new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
	// }

	public List<BookDto> search(String title, String isbn, String firstName, String lastName, List<String> categories,
			Integer page, Integer size) {
		return this.bookAssembler.toDtos(this.bookRepository.search(title, isbn, firstName, lastName,
				this.bookAssembler.toEnumForQuery(categories),
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"))));
	}

	public BookDto borrowBook(Long bookId, Long borrowerId) {

		BorrowerEntity borrower = this.borrowerService.findOne(borrowerId);
		BookEntity book = this.bookRepository.findOne(bookId);

		List<BorrowDetailEntity> borrowDetails = book.getBorrowDetails();

		Boolean isBorrowed = borrowDetails.stream().anyMatch(p -> !p.getIsReturned());

		if (!isBorrowed) {
			BorrowDetailEntity borrowDetail = new BorrowDetailEntity(false, LocalDate.now(),
					LocalDate.now().plusMonths(AppConf.MOTHS_TO_RETURN_BOOK), book, borrower);

			borrowDetails.add(borrowDetail);
			book.setBorrowDetails(borrowDetails);
			this.bookRepository.save(book);

			BookDto bookDto = this.bookAssembler.toDto(book);
			setBorrower(bookDto, book);

			return bookDto;
		} else {
			throw new ApplicationCustomException("Book is already borrowed.");
		}
	}

	public BookDto returnBook(Long bookId, Long borrowerId) {

		BorrowerEntity borrower = this.borrowerService.findOne(borrowerId);
		BookEntity book = this.bookRepository.findOne(bookId);

		List<BorrowDetailEntity> borrowDetails = book.getBorrowDetails();

		Optional<BorrowDetailEntity> borrowDetail = borrowDetails.stream()
				.filter(p -> !p.getIsReturned() && p.getBorrower().getId().equals(borrower.getId())).findFirst();

		Boolean isBorrowed = borrowDetail.isPresent();

		if (isBorrowed) {
			borrowDetail.get().setIsReturned(true);
			borrowDetail.get().setReturnDate(LocalDate.now());
			this.bookRepository.save(book);

			BookDto bookDto = this.bookAssembler.toDto(book);
			setBorrower(bookDto, book);

			return bookDto;
		} else {
			throw new ApplicationCustomException("Book is already borrowed.");
		}
	}

	public List<String> getBookCoverTypes() {
		return this.bookAssembler.getBookCoverTypes();
	}

	public List<String> getBookCategoryTypes() {
		return this.bookAssembler.getBookCategoryTypes();
	}

	private void setBorrower(BookDto bookDto, BookEntity bookEntity) {
		Optional<BorrowDetailEntity> borrowDetails = bookEntity.getBorrowDetails().stream()
				.filter(p -> !p.getIsReturned()).findAny();

		if (borrowDetails.isPresent()) {
			bookDto.setBorrower(this.borrowerAssembler.toDto(borrowDetails.get().getBorrower()));
		}
	}

	public BookDto save(BookDto bookDto) throws ParseException {
		BookEntity bookEntity = this.bookAssembler.toEntity(bookDto,
				this.authorService.findOne(bookDto.getAuthor().getId()));
		bookEntity = this.bookRepository.save(bookEntity);
		bookDto.setId(bookEntity.getId());
		return bookDto;
	}

	public BookDto create(BookDto bookDto) throws ParseException {

		AuthorEntity author;

//		Optional<AuthorEntity> authorEntity = this.authorService.find(bookDto.getAuthor().getFirstName(),
//				bookDto.getAuthor().getLastName());
//
//		if (!authorEntity.isPresent()) {
//			author = this.authorAssembler.toEntity(bookDto.getAuthor());
//			author.setId(5L);
//			
//			author = this.authorService.save(author);
//		} else {
//			author = authorEntity.get();
//		}

		 author = this.authorService.findOne(1L);
		
		BookEntity bookEntity = this.bookAssembler.toEntity(bookDto, author);
		bookEntity.setBorrowDetails(new ArrayList<BorrowDetailEntity>());
		bookEntity = this.bookRepository.save(bookEntity);

		return this.bookAssembler.toDto(bookEntity);
	}

	public BookDto update(BookDto bookDto) throws ParseException {
		BookEntity bookEntity = this.bookRepository.getOne(bookDto.getId());
		AuthorEntity authorEntity = bookEntity.getAuthor();

		// new author
		Optional<Long> authorId = Optional.of(bookDto.getAuthor().getId());

		if (!authorId.isPresent()) {
			authorEntity = this.authorAssembler.toEntity(bookDto.getAuthor());
			authorEntity = this.authorService.save(authorEntity);
		}

		this.bookAssembler.updateEntity(bookEntity, bookDto, authorEntity);

		bookEntity = this.bookRepository.save(bookEntity);
		return this.bookAssembler.toDto(bookEntity);
	}
}
