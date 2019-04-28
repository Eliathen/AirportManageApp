package exceptions;

public class LoginAlreadyExistException extends Exception {
    public LoginAlreadyExistException() {
    }

    public LoginAlreadyExistException(String message) {
        super(message);
    }
}
