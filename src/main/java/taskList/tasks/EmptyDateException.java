package taskList.tasks;

public class EmptyDateException extends RuntimeException {

    public EmptyDateException() {
        super("Please enter a date.");
    }

}
