package exceptions;

public class PassengerAlreadyExistException extends Exception {
    public PassengerAlreadyExistException(String message) {
        super(message);
    }
}
