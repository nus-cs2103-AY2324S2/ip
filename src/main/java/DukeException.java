public class DukeException extends Exception {
    private final String errorMessage;

    DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
