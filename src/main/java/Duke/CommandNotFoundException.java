package duke;

public class CommandNotFoundException extends DukeException{
    public CommandNotFoundException(String command) {
        super(command + " is not a valid command!");
    }
}
