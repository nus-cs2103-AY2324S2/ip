package snomexceptions;

public class InvalidCommandTaskDoneException extends InvalidCommandException{
    public InvalidCommandTaskDoneException() {
        super("A task that is already done cannot be marked as done");
    }
}
