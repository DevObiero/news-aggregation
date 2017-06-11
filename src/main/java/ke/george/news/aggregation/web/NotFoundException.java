package ke.george.news.aggregation.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class extends the default behaviour of Exception class
 * Return 404 instead of 500 status code
 *
 * @author george
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
    private NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
