public class DukeException extends Exception {
    public DukeException(String message) {
        super("You have input an invalid command! " + message);
    }
}
