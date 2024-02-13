package duke.exceptions;

public class InvalidCommandException extends DukeException{
    public InvalidCommandException() {
        super("Invalid command. Use help to for more info");
    }
}
