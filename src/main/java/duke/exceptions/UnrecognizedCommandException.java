package duke.exceptions;

public class UnrecognizedCommandException extends Exception {
    UnrecognizedCommandException(String commandName) {
        super(commandName);
    }
}
