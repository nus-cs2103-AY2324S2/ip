package snomexceptions;

public class InvalidCommandFormatException extends InvalidCommandException {

    public InvalidCommandFormatException() {
        super("Please make sure your command is of the valid format");
    }
}
