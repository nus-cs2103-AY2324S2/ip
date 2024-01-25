public class DukeException extends RuntimeException {
    public static final String NON_EMPTY_DESC = "The description of a %s cannot be empty!";
    public static final String UNKNOWN_CMD = "I'm sorry, but I don't know what %s means :-(";
    public DukeException(String message) {
        super(String.format("Hmm, %s", message));
    }
}
