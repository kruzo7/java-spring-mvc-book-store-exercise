package pl.exercise.spring.mvc.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pl.exercise.spring.mvc.configuration.AppConf;

@Entity
@Table(name = "BORROW_DETAIL")
@SequenceGenerator(initialValue = AppConf.SEQ_START, name = "BORROW_DETAIL_SEQ",  allocationSize = 1, sequenceName = "BORROW_DETAIL_SEQ")
public class BorrowDetailEntity extends EntityBase {

	private static final long serialVersionUID = 10L;

	@Column(name = "IS_RETURNED")
	private Boolean isReturned;

	@Column(name = "BORROW_DATE")
	private LocalDate borrowDate;

	@Column(name = "RETURN_DATE")
	private LocalDate returnDate;

	@Column(name = "MAX_RETURN_DATE")
	private LocalDate maxReturnDate;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "LIBRARY_ITEM_ID")
	private LibraryItemBase libraryItem;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "BORROWER_ID")
	private BorrowerEntity borrower;

	protected BorrowDetailEntity() {
		super();
	}

	public BorrowDetailEntity(Boolean isReturned, LocalDate borrowDate, LocalDate maxReturnDate,
			LibraryItemBase libraryItem, BorrowerEntity borrower) {
		super();
		this.isReturned = isReturned;
		this.borrowDate = borrowDate;		
		this.maxReturnDate = maxReturnDate;
		this.libraryItem = libraryItem;
		this.borrower = borrower;
	}

	public Boolean getIsReturned() {
		return isReturned;
	}

	public LibraryItemBase getLibraryItem() {
		return libraryItem;
	}

	public BorrowerEntity getBorrower() {
		return borrower;
	}

	public void setIsReturned(Boolean isReturned) {
		this.isReturned = isReturned;
	}

	public void setLibraryItem(LibraryItemBase libraryItem) {
		this.libraryItem = libraryItem;
	}

	public void setBorrower(BorrowerEntity borrower) {
		this.borrower = borrower;
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
}
