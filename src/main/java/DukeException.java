public class DukeException extends Exception {
    public DukeException(String message) {
        super("DevGPT error: " + message);
    }
}
