package board.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
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
	private String detailDate;
	
}
