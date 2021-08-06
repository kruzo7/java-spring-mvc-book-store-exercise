package pl.exercise.spring.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pl.exercise.spring.mvc.configuration.AppConf;

@Entity
@Table(name = "NEWSPAPER_ATTACHMENT")
@SequenceGenerator(initialValue = AppConf.SEQ_START, name = "NEWSPAPER_ATTACHMENT_SEQ",  allocationSize = 1, sequenceName = "NEWSPAPER_ATTACHMENT_SEQ")
public class NewspaperAttachmentEntity extends EntityBase {

	private static final long serialVersionUID = 10L;

	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private AttachmentType attachmentType;

	@Column(name = "NAME")
	private String name;

	@OneToOne(mappedBy = "newspaperAttachment", fetch = FetchType.EAGER)
	public NewspaperEntity newspaper;

	protected NewspaperAttachmentEntity() {
		super();
	}

	public NewspaperAttachmentEntity(String name, AttachmentType attachmentType) {
		super();
		this.name = name;
		this.attachmentType = attachmentType;
	}

	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	public String getName() {
		return name;
	}

	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NewspaperEntity getNewspaper() {
		return newspaper;
	}

	public void setNewspaper(NewspaperEntity newspaper) {
		this.newspaper = newspaper;
	}

}
