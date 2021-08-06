package pl.exercise.spring.mvc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import pl.exercise.spring.mvc.configuration.AppConf;

@Entity
@Table(name = "BORROWER")
@SequenceGenerator(initialValue = AppConf.SEQ_START, name = "BORROWER_SEQ",  allocationSize = 1, sequenceName = "BORROWER_SEQ")
public class BorrowerEntity extends PersonEntityBase {

	private static final long serialVersionUID = 100L;

	@OneToMany(mappedBy = "borrower")
	@Fetch(FetchMode.SELECT)
	private List<BorrowDetailEntity> borrowDetails;

	protected BorrowerEntity() {
		super();
	}

	public BorrowerEntity(String firstName, String lastName) {
		super(firstName, lastName);
	}
}
