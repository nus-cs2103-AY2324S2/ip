package duke.exceptions;

public class DukeException extends Exception {
    // Ex: Invalid commands
    public DukeException (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
