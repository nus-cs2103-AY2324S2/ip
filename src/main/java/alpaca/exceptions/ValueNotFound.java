package alpaca.exceptions;

public class ValueNotFound extends Exception {
    public ValueNotFound(String errorMessage) {
        super(errorMessage);
    }
}