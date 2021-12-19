package common.code;

public enum ErrorCode {

	DATABASE_ACCESS_ERROR("데이터 오류가 발생했습니다."),
	HTTP_CONNECT_ERROR("HTTP통신 중 에러가 발생하였습니다."),
	FAILED_FILE_UPLOAD_ERROR("파일업로드에 실패했습니다.");

	
	public final String MESSAGE;
	public final String URL;
	
	ErrorCode(String msg){
		this.MESSAGE = msg;
		this.URL = "/index";
	}
	
	ErrorCode(String msg, String url){
		this.MESSAGE = msg;
		this.URL = url;
	}
	

}
