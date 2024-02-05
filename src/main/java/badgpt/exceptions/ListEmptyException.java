package badgpt.exceptions;

public class ListEmptyException extends TaskException {
    public ListEmptyException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "There are no tasks.";
    }
}
