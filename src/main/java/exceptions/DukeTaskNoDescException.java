package exceptions;

public class DukeTaskNoDescException extends DukeException {
    public DukeTaskNoDescException() {
        super("Hmm...the task description seems empty, please add some description :')");
    }
}
