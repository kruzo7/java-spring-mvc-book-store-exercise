package pl.exercise.spring.mvc.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "BOOK")
@DiscriminatorValue("BOOK")
public class BookEntity extends LibraryItemBase {

	private static final long serialVersionUID = 100L;
	
	@Column(name = "COVER_TYPE", nullable = true)
	@Enumerated(EnumType.STRING)
	private BookCoverType coverType;

	@Column(name = "PUBLISH_DATE")
	protected LocalDateTime publishDate;

	@Column(name = "NUMBER_OF_PAGES")
	protected Long numberOfPages;

	@Basic(fetch = FetchType.EAGER)
	@ElementCollection
	@JoinTable(name = "BOOK_CATEGORY", joinColumns = @JoinColumn(name = "BOOK_ID"))
	@Column(name = "CATEGORY")
	@Enumerated(EnumType.STRING)
	@Fetch(FetchMode.SELECT)
	private Set<BookCategoryType> categories;	  
	
	protected BookEntity() {
		super();
	}

	public BookEntity(BookCoverType coverType, LocalDateTime publishDate, Long numberOfPages,
			Set<BookCategoryType> categories, String title, AuthorEntity author, String isbn) {
		super(title, author, isbn);
		this.coverType = coverType;
		this.publishDate = publishDate;
		this.numberOfPages = numberOfPages;
		this.categories = categories;
	}

	public BookCoverType getCoverType() {
		return coverType;
	}

	public void setCoverType(BookCoverType coverType) {
		this.coverType = coverType;
	}

	public LocalDateTime getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDateTime publishDate) {
		this.publishDate = publishDate;
	}

	public Long getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Long numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public Set<BookCategoryType> getCategories() {
		return categories;
	}

	public void setCategories(Set<BookCategoryType> categories) {
		this.categories = categories;
	}
}
