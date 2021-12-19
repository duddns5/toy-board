package common.file;

import java.sql.Date;

import lombok.Data;

@Data
public class FileDTO {

	private int flIdx;
	private int bdIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private Date regDate;
	private int isDel;
	
	public String getDownloadURL() {
		return "/file/" + savePath + renameFileName + "?originName=" + originFileName;
	}
}
