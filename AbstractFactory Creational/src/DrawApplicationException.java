
public class DrawApplicationException extends RuntimeException {
	private String message;

	public DrawApplicationException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
