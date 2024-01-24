package chatbot.exceptions;

public class AlreadyMarkedException extends DukeException {
    public static final String ERRSTR = "This task has already been marked as done!";
    public AlreadyMarkedException() {
        super(ERRSTR);
    }
}
