package exceptions;

public class IncorrectDateError extends Exception{
    public IncorrectDateError() {

        super();
    }

    public IncorrectDateError(String message) {

        super(message);
    }
}
