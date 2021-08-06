package pl.exercise.spring.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersonEntityBase extends EntityBase {

	private static final long serialVersionUID = 10L;

	@Column(name = "FIRST_NAME")
	@Size(min = 3, max = 50)
	protected String firstName;

	@Column(name = "LAST_NAME")
	@Size(min = 3, max = 50)
	protected String lastName;

	protected PersonEntityBase() {
		super();
	}

	public PersonEntityBase(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
