package board.model.dto;

import java.sql.Date;

public class Board {
	
	private int bdIdx;
	private String userId;
	private String password;
	private String title;
	private String content;
	private Date regDate;
	private int viewCount;
	private int recCount;
	private int isDel;
	
	public int getBdIdx() {
		return bdIdx;
	}
	public void setBdIdx(int bdIdx) {
		this.bdIdx = bdIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getRecCount() {
		return recCount;
	}
	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	@Override
	public String toString() {
		return "Board [bdIdx=" + bdIdx + ", userId=" + userId + ", password=" + password + ", title=" + title
				+ ", content=" + content + ", regDate=" + regDate + ", viewCount=" + viewCount + ", recCount="
				+ recCount + ", isDel=" + isDel + "]";
	}
}
