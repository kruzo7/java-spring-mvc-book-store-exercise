package pl.exercise.spring.mvc.entity;

import pl.exercise.spring.mvc.configuration.AppConf;

import java.util.Optional;

import javax.persistence.*;
import javax.validation.constraints.Min;

//NOTE: In this EDU task this entity is not used but it must remain.
@Entity
@Table(name = "NEWSPAPER")
@DiscriminatorValue("NEWSPAPER")
public class NewspaperEntity extends LibraryItemBase {

	private static final long serialVersionUID = 100L;

	// How many newspaper copies are printed per issue.
	@Column(name = "CONTROLLED_CIRCULATION")
	@Min(value = 1, message = "Value must be greater than 1.")
	private Long controlledCirculation;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ATTACHMENT_ID", nullable = true)
	public NewspaperAttachmentEntity newspaperAttachment;

	protected NewspaperEntity() {
		super();
	}

	public NewspaperEntity(String title, AuthorEntity author, String isbn, Long controlledCirculation,
			NewspaperAttachmentEntity newspaperAttachment) {
		super(title, author, isbn);
		this.controlledCirculation = controlledCirculation;
		this.newspaperAttachment = newspaperAttachment;
	}

	public Long getControlledCirculation() {
		return controlledCirculation;
	}

	public void setControlledCirculation(Long controlledCirculation) {
		this.controlledCirculation = controlledCirculation;
	}

	public Optional<NewspaperAttachmentEntity> getNewspaperAttachment() {
		return Optional.of(newspaperAttachment);
	}

	public void setNewspaperAttachment(Optional<NewspaperAttachmentEntity> newspaperAttachment) {
		this.newspaperAttachment = newspaperAttachment.orElse(null);
	}

}
