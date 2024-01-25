public class NoTaskTypeException extends Exception {
    public NoTaskTypeException() {
        super("Sorry! I don't understand your command!\n" +
                "To add a new task, please use the keyword 'todo', 'event', or 'deadline' before describing the task");
    }
}
