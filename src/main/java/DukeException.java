public class DukeException extends IllegalArgumentException {

    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Exception e) {
        super(message, e);
    }

    public static DukeException createLoadingError() {
        return new DukeException("Error loading files");
    }

    public static DukeException showLoad(Exception e) {
        return new DukeException("Error loading files", e);
    }
}
