package hello.exception.exception;

import javax.swing.text.TableView;

public class UserException extends RuntimeException {

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    protected UserException(String message, Throwable cause, boolean enableSupperession, boolean writableStackTrace) {
        super(message, cause, enableSupperession, writableStackTrace);
    }
}
