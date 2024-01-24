public class MissingEventException extends DukeException {
    public MissingEventException() {
        super("Please enter a start and end for the event.");
    }
}