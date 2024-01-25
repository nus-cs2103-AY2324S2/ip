public class DukeException extends Exception {
    public DukeException(String message) {
        super("You have input your task in an invalid format! " + message);
    }
}
