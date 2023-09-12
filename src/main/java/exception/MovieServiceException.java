package exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 5235209810479903613L;

    public ServiceException(final String message) {
        super("Exception occured at service layer because of" + message);
    }
}
