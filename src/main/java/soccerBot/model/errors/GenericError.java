package soccerBot.model.errors;

public class GenericError extends Exception {

	private static final long serialVersionUID = 1L;

	public GenericError() {
	}

	public GenericError(String message) {
		super(message);
	}

	public GenericError(Throwable cause) {
		super(cause);
	}

	public GenericError(String message, Throwable cause) {
		super(message, cause);
	}

}
