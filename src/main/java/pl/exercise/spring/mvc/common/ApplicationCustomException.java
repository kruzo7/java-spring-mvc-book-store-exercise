package pl.exercise.spring.mvc.common;

public class ApplicationCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApplicationCustomException(String message) {
		super(message);
	}
}
