package kr.datasolution.webcrawler.crawleddata;

public class CrawledData {
	private	String title;
	private String content;
	private String name;
	private String date;
	
	public CrawledData() {
		// TODO Auto-generated constructor stub
	}

	public CrawledData(String title, String content, String name, String date) {
		super();
		this.title = title;
		this.content = content;
		this.name = name;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
