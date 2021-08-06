package pl.exercise.spring.mvc.dto;

public class DtoBase {

	private Long id;

	private String itemRel;

	private String itemUri;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemRel() {
		return itemRel;
	}

	public void setItemRel(String itemRel) {
		this.itemRel = itemRel;
	}

	public String getItemUri() {
		return itemUri;
	}

	public void setItemUri(String itemUri) {
		this.itemUri = itemUri;
	}

}
