package badgpt.exceptions;

public class ListFullException extends TaskException {
    public ListFullException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "dude maybe finish something first";
    }
}
