package board.model;

//model(data)
//index, name, title, content, count 데이터를 가진다.
public class BoardDTO {
	private int index; // 게시물 번호
	private String name; // 작성자 이름
	private String title; // 게시물 제목
	private String content; // 게시물 내용
	private int recommendation; // 추천 수
	private int viewCount;

	// Getter and Setter
	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRecommendation() {
		return this.recommendation;
	}

	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "BoardDTO [index=" + index + ", name=" + name + ", title=" + title + ", content=" + content
				+ ", recommendation=" + recommendation + ", viewCount=" + viewCount + "]";
	}
}
