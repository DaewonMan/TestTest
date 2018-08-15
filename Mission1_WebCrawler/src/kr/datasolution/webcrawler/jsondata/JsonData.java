package kr.datasolution.webcrawler.jsondata;

public class JsonData {
	private String url_addr;
	private String notice_tag;
	private String notices_tag;
	private String nextpages_tag;
	private String url_add_info;
	private String next_url_add_info;
	private String title_tag; 		
	private String name_tag; 		
	private String name_img_tag; 		
	private String content_tag; 		
	private String date_tag;
	private String date_modify_tag;
	private int date_length; 		
	private String current_year;
	private int page_count;
	
	public JsonData() {
		// TODO Auto-generated constructor stub
	}

	public JsonData(String url_addr, String notice_tag, String notices_tag, String nextpages_tag, String url_add_info,
			String next_url_add_info, String title_tag, String name_tag, String name_img_tag, String content_tag,
			String date_tag, String date_modify_tag, int date_length, String current_year, int page_count) {
		super();
		this.url_addr = url_addr;
		this.notice_tag = notice_tag;
		this.notices_tag = notices_tag;
		this.nextpages_tag = nextpages_tag;
		this.url_add_info = url_add_info;
		this.next_url_add_info = next_url_add_info;
		this.title_tag = title_tag;
		this.name_tag = name_tag;
		this.name_img_tag = name_img_tag;
		this.content_tag = content_tag;
		this.date_tag = date_tag;
		this.date_modify_tag = date_modify_tag;
		this.date_length = date_length;
		this.current_year = current_year;
		this.page_count = page_count;
	}

	public String getUrl_addr() {
		return url_addr;
	}

	public void setUrl_addr(String url_addr) {
		this.url_addr = url_addr;
	}

	public String getNotice_tag() {
		return notice_tag;
	}

	public void setNotice_tag(String notice_tag) {
		this.notice_tag = notice_tag;
	}	

	public String getNotices_tag() {
		return notices_tag;
	}

	public void setNotices_tag(String notices_tag) {
		this.notices_tag = notices_tag;
	}

	public String getNextpages_tag() {
		return nextpages_tag;
	}

	public void setNextpages_tag(String nextpages_tag) {
		this.nextpages_tag = nextpages_tag;
	}

	public String getUrl_add_info() {
		return url_add_info;
	}

	public void setUrl_add_info(String url_add_info) {
		this.url_add_info = url_add_info;
	}

	public String getTitle_tag() {
		return title_tag;
	}

	public void setTitle_tag(String title_tag) {
		this.title_tag = title_tag;
	}

	public String getName_tag() {
		return name_tag;
	}

	public void setName_tag(String name_tag) {
		this.name_tag = name_tag;
	}

	public String getContent_tag() {
		return content_tag;
	}

	public void setContent_tag(String content_tag) {
		this.content_tag = content_tag;
	}

	public String getDate_tag() {
		return date_tag;
	}

	public void setDate_tag(String date_tag) {
		this.date_tag = date_tag;
	}

	public int getDate_length() {
		return date_length;
	}

	public void setDate_length(int date_length) {
		this.date_length = date_length;
	}

	public int getPage_count() {
		return page_count;
	}

	public void setPage_count(int page_count) {
		this.page_count = page_count;
	}
	
	public String getCurrent_year() {
		return current_year;
	}

	public void setCurrent_year(String current_year) {
		this.current_year = current_year;
	}

	public String getName_img_tag() {
		return name_img_tag;
	}

	public void setName_img_tag(String name_img_tag) {
		this.name_img_tag = name_img_tag;
	}

	public String getNext_url_add_info() {
		return next_url_add_info;
	}

	public void setNext_url_add_info(String next_url_add_info) {
		this.next_url_add_info = next_url_add_info;
	}

	public String getDate_modify_tag() {
		return date_modify_tag;
	}

	public void setDate_modify_tag(String date_modify_tag) {
		this.date_modify_tag = date_modify_tag;
	}
}
