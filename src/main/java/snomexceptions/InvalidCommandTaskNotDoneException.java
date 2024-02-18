package snomexceptions;

public class InvalidCommandTaskNotDoneException extends InvalidCommandException {

    public InvalidCommandTaskNotDoneException() {
        super("An task that is not done cannot be marked as undone");
    }
}
