package pl.exercise.spring.mvc.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pl.exercise.spring.mvc.configuration.AppConf;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISCRIMINATOR_TYPE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "LIBRARY_ITEM")
@SequenceGenerator(initialValue = AppConf.SEQ_START, name = "LIBRARY_ITEM_SEQ",  allocationSize = 1, sequenceName = "LIBRARY_ITEM_SEQ")
public abstract class LibraryItemBase extends EntityBase {

	private static final long serialVersionUID = 10L;

	@Column(name = "TITLE")
	@NotNull(message = "Title can't be null.")
	@Size(min = 3, max = 400, message = "Title length must be between 3 and 400.")
	protected String title;

	@Column(name = "ISBN", unique = true)
	// Only Test regexp pattern, not realworld example.
	@Pattern(regexp = "\\d{3}\\-.+?", message = "Invalid ISBN format. Must starts with 000-")
	protected String isbn;

	@ManyToOne
	protected AuthorEntity author;

	@OneToMany(mappedBy = "libraryItem", cascade = CascadeType.ALL)
	private List<BorrowDetailEntity> borrowDetails;

	// Not present in DB.
	@Transient
	private String temporaryCoverImageUrl;

	protected LibraryItemBase() {
		super();
	}

	public LibraryItemBase(String title, AuthorEntity author, String isbn) {
		this.title = title;
		this.isbn = isbn;
		this.author = author;
	}

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

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}

	public List<BorrowDetailEntity> getBorrowDetails() {
		return borrowDetails;
	}

	public void setBorrowDetails(List<BorrowDetailEntity> borrowDetails) {
		this.borrowDetails = borrowDetails;
	}

	public String getTemporaryCoverImageUrl() {
		return temporaryCoverImageUrl;
	}

	public void setTemporaryCoverImageUrl(String temporaryCoverImageUrl) {
		this.temporaryCoverImageUrl = temporaryCoverImageUrl;
	}
}
