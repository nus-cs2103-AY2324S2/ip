public class MissingInputException extends DukeException {

    public MissingInputException(
            String displayMessage) {
        super("Missing one or more inputs for operations", displayMessage);
    }
}
