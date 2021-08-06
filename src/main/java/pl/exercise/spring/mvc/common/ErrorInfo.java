package pl.exercise.spring.mvc.common;

public class ErrorInfo {
	public final String url;
	public final String message;
	public final String statusCode;

	public ErrorInfo(String statusCode, String url, Exception ex) {
		this.statusCode = statusCode;
		this.url = url;
		this.message = ex.toString();
	}
}
