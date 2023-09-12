package exception;

public class MovieServiceException extends RuntimeException {

    private static final long serialVersionUID = 5235209810479903613L;

    public MovieServiceException(final String message) {
        super("Exception occured at service layer because of" + message);
    }
}
