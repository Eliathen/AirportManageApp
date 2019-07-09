package exceptions;


public class LoginAlreadyExistException extends Exception {

    public LoginAlreadyExistException(String message) {
        super(message);
    }

}
