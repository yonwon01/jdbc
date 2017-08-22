package com.bigdata2017.jdbc.bookshop.vo;

public class BookVo {//지금 vo는 단지 값을 전달하고 받고

	private Long no;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(Long authorNo) {
		this.authorNo = authorNo;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", authorName=" + authorName +" ,state=" + state  
				 + "]";
	}
	private String title;
	private String state;
	private Long authorNo;
	private String authorName;  //select문에서 join일어나야 함
	
}
