package pl.exercise.spring.mvc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pl.exercise.spring.mvc.configuration.AppConf;

@Entity
@Table(name = "AUTHOR")
@SequenceGenerator(initialValue = AppConf.SEQ_START, name = "AUTHOR_SEQ", allocationSize = 1, sequenceName = "AUTHOR_SEQ")
public class AuthorEntity extends PersonEntityBase {

	private static final long serialVersionUID = 100L;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	private List<LibraryItemBase> libraryItems;

	protected AuthorEntity() {
		super();
	}

	public AuthorEntity(String firstName, String lastName) {
		super(firstName, lastName);
	}

	public List<LibraryItemBase> getLibraryItems() {
		return libraryItems;
	}

	public void setLibraryItems(List<LibraryItemBase> libraryItems) {
		this.libraryItems = libraryItems;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
