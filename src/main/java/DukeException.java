public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super("Something went wrong...\n" + message);
    }
}
