package pl.exercise.spring.mvc.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import pl.exercise.spring.mvc.configuration.AppConf;

public class BorrowDetailDto extends DtoBase {

	private Boolean isReturned;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConf.DATE_FORMAT_JSON)
	private LocalDate borrowDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConf.DATE_FORMAT_JSON)
	private LocalDate returnDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConf.DATE_FORMAT_JSON)
	private LocalDate maxReturnDate;

	private Long libraryItemId;

	private BorrowerDto borrower;

	public Boolean getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(Boolean isReturned) {
		this.isReturned = isReturned;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDate getMaxReturnDate() {
		return maxReturnDate;
	}

	public void setMaxReturnDate(LocalDate maxReturnDate) {
		this.maxReturnDate = maxReturnDate;
	}

	public Long getLibraryItemId() {
		return libraryItemId;
	}

	public void setLibraryItemId(Long libraryItemId) {
		this.libraryItemId = libraryItemId;
	}

	public BorrowerDto getBorrower() {
		return borrower;
	}

	public void setBorrower(BorrowerDto borrower) {
		this.borrower = borrower;
	}
}
