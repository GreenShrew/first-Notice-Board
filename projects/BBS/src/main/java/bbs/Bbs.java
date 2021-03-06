package bbs; // 자바 bins class
// bins란, 하나의 게시글 정보를 담을 수 있는 인스턴스를 만들기 위한 틀.
// 게시판에서 사용 될 게시판 데이터베이스를 구축.

public class Bbs {
	
	private int bbsID;
	private String bbsTitle;
	private String userID;
	private String bbsDate;		// String이 아닌 날짜를 분류하는 다른 자료형을 쓰지만 여기서는 그냥 문자열로 관리할 예정
	private String bbsContent;
	private int bbsAvailable;
	
// 우클릭, source, Generate Getters and Setters
	public int getBbsID() {
		return bbsID;
	}
	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBbsDate() {
		return bbsDate;
	}
	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public int getBbsAvailable() {
		return bbsAvailable;
	}
	public void setBbsAvailable(int bbsAvailable) {
		this.bbsAvailable = bbsAvailable;
	}
	
}
