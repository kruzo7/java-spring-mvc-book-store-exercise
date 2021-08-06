package pl.exercise.spring.mvc.assembler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import pl.exercise.spring.mvc.dto.BookDto;
import pl.exercise.spring.mvc.entity.AuthorEntity;
import pl.exercise.spring.mvc.entity.BookCategoryType;
import pl.exercise.spring.mvc.entity.BookCoverType;
import pl.exercise.spring.mvc.entity.BookEntity;

@Component
public class BookAssembler {

	@Autowired

	private AuthorAssembler authorAssembler;

	public BookDto toDto(BookEntity book) {
		BookDto bookDto = new BookDto();
		bookDto.setId(book.getId());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setTitle(book.getTitle());
		bookDto.setAuthor(this.authorAssembler.toDto(book.getAuthor()));
		bookDto.setNumberOfPages(book.getNumberOfPages());
		bookDto.setCategories(UtilsEnum.toString(book.getCategories()));
		bookDto.setCoverType(book.getCoverType().name());
		bookDto.setPublishDate(book.getPublishDate());
		bookDto.setChecked(false);
		return bookDto;
	}

	public BookEntity toEntity(BookDto bookDto, AuthorEntity authorEntity) throws ParseException {
		return new BookEntity(BookCoverType.valueOf(bookDto.getCoverType()), bookDto.getPublishDate(),
				bookDto.getNumberOfPages(),
				bookDto.getCategories().stream().map(p -> BookCategoryType.valueOf(p)).collect(Collectors.toSet()),
				bookDto.getTitle(), authorEntity, bookDto.getIsbn());
	}

	public List<BookDto> toDtos(List<BookEntity> books) {
		return books.stream().map(this::toDto).collect(Collectors.toList());
	}

	public List<BookDto> toDtos(Page<BookEntity> books) {
		if (books.hasContent()) {
			return books.getContent().stream().map(this::toDto).collect(Collectors.toList());
		} else {
			return new ArrayList<BookDto>();
		}
	}

	public List<BookCategoryType> toEnumForQuery(List<String> categories) {

		if(Optional.ofNullable(categories).isPresent() == false) {
			return new ArrayList<BookCategoryType>(EnumSet.allOf(BookCategoryType.class));
		}

		if (!categories.isEmpty()) {
			return categories.stream().map(p -> BookCategoryType.valueOf(p)).collect(Collectors.toList());
		} else {
			return new ArrayList<BookCategoryType>(EnumSet.allOf(BookCategoryType.class));
		}
	}

	public List<String> getBookCoverTypes() {
		return new ArrayList<BookCoverType>(EnumSet.allOf(BookCoverType.class)).stream().map(p -> p.name())
				.collect(Collectors.toList());
	}

	public List<String> getBookCategoryTypes() {
		return new ArrayList<BookCategoryType>(EnumSet.allOf(BookCategoryType.class)).stream().map(p -> p.name())
				.collect(Collectors.toList());

	}

	public void updateEntity(BookEntity bookEntity, BookDto bookDto, AuthorEntity authorEntity) {

		bookEntity.setAuthor(authorEntity);
		bookEntity.setCategories(
				bookDto.getCategories().stream().map(p -> BookCategoryType.valueOf(p)).collect(Collectors.toSet()));
		bookEntity.setCoverType(BookCoverType.valueOf(bookDto.getCoverType()));
		bookEntity.setIsbn(bookDto.getIsbn());
		bookEntity.setNumberOfPages(bookDto.getNumberOfPages());
		bookEntity.setPublishDate(bookDto.getPublishDate());
		bookEntity.setTitle(bookDto.getTitle());

		// don't update those values
		// setId(id);
		// setBorrowDetails(borrowDetails); //this set is operated on service: borrow, return
	}
}
