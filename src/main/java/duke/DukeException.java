package duke;

public class DukeException extends Exception {
    private final String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
