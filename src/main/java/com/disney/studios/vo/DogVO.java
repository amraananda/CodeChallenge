package com.disney.studios.vo;

//@SqlResultSetMapping(name = "findAllDogsGroupedByBreedSortedByUpCount", classes = { @ConstructorResult(targetClass = DogVO.class, columns = {
//		@ColumnResult(name = "id"), @ColumnResult(name = "breed"), @ColumnResult(name = "url"),
//		@ColumnResult(name = "upCount"), @ColumnResult(name = "downCount") }) })
public class DogVO {

	private Long id;

	private String url;

	private String breed;

	private Long upCount;

	private Long downCount;
	
	public DogVO(Long id, String breed,String url, Long upCount, Long downCount) {
		super();
		this.id = id;
		this.url = url;
		this.breed = breed;
		this.upCount = upCount;
		this.downCount = downCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Long getUpCount() {
		return upCount;
	}

	public void setUpCount(Long upCount) {
		this.upCount = upCount;
	}

	public Long getDownCount() {
		return downCount;
	}

	public void setDownCount(Long downCount) {
		this.downCount = downCount;
	}

}
