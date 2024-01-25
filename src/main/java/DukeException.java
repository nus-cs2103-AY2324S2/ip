public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("holup!! %s", super.getMessage());
    }
}