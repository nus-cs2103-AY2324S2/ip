public class JayneException extends Exception {
    public JayneException(String message) {
        super(message);
    }
    //Deadline
    public static JayneException deadlineException() {
        return new JayneException("Where can have empty deadline time, enter deadline lah. E.g. deadline return book /by Sunday");
    }

    //Unmark
    public static JayneException unmarkException() {
        return new JayneException("??? Invalid task number. Enter a valid number please. E.g. unmark 1");
    }

    public static JayneException unmarkTaskExistException(int taskNumber) {
        return new JayneException("Task number " + taskNumber + " does not exist. E.g. unmark 1");
    }

    public static JayneException unmarkEmptyException() {
        return new JayneException("What are you doing? The number of the task to unmark cannot be empty lah. E.g. unmark 1");
    }

    //Delete
    public static JayneException deleteEmptyException() {
        return new JayneException("The task number to delete cannot be empty. E.g. delete 2");
    }

    public static JayneException deleteInvalidException() {
        return new JayneException("??? Invalid task number. Enter a valid number please. E.g. delete 2");
    }

    public static JayneException deleteExistException(int taskNumber) {
        return new JayneException("Task number " + taskNumber + " does not exist. E.g. delete 2");
    }

    //to-do
    public static JayneException todoException() {
        return new JayneException("Huh?? why your description of a todo is empty! Please enter description. E.g. todo read book");
    }

    //event
    public static JayneException emptyEventException() {
        return new JayneException("The description of an event cannot be empty lah. E.g. event project meeting /from Mon 2pm /to 4pm");
    }
    public static JayneException eventStartException() {
        return new JayneException("The event start time cannot be empty. E.g. event project meeting /from Mon 2pm /to 4pm");
    }
    public static JayneException eventEndException() {
        return new JayneException("The event end time cannot be empty please. E.g. event project meeting /from Mon 2pm /to 4pm");
    }

    //Mark
    public static JayneException markEmptyException() {
        return new JayneException("The task number to mark cannot be empty. E.g. mark 1");
    }
    public static JayneException markTaskExistException(int taskNumber) {
        return new JayneException("Task number " + taskNumber + " does not exist. E.g. mark 1");
    }

    public static JayneException markInvalidTaskException() {
        return new JayneException("??? Invalid task number. Enter a valid number please. E.g. mark 1");
    }
}
