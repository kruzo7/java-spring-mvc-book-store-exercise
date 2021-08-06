package pl.exercise.spring.mvc.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import pl.exercise.spring.mvc.configuration.AppConf;

public class BookDto extends DtoBase {

	private String title;

	private String isbn;

	private AuthorDto author;

	private Long numberOfPages;

	private String coverType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConf.DATE_FORMAT_JSON)
	public LocalDateTime publishDate;

	private List<BorrowDetailDto> borrowDetails;

	private List<String> categories;

	private BorrowerDto borrower;

	// For custom UI operations.
	private Boolean checked;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public AuthorDto getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDto author) {
		this.author = author;
	}

	public List<BorrowDetailDto> getBorrowDetails() {
		return borrowDetails;
	}

	public void setBorrowDetails(List<BorrowDetailDto> borrowDetails) {
		this.borrowDetails = borrowDetails;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public Long getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Long numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getCoverType() {
		return coverType;
	}

	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}

	public LocalDateTime getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDateTime publishDate) {
		this.publishDate = publishDate;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public BorrowerDto getBorrower() {
		return borrower;
	}

	public void setBorrower(BorrowerDto borrower) {
		this.borrower = borrower;
	}

}
