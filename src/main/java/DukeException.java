public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("ERROR! " + errorMessage);
    }
}