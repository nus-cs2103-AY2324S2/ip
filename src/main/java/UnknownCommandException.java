public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("Error. Unable to recognize such command.\n"
                + "Supported List of Commands:\n"
                + "- " +  ToDo.CREATE_TODO_FORMAT + ": Adds new ToDo task.\n"
                + "- " +  Deadline.CREATE_DEADLINE_FORMAT + ": Adds new Deadline task.\n"
                + "- " +  Event.CREATE_EVENT_FORMAT + ": Adds new Event task.\n"
                + "- mark <task-number>: Marks task at index <task-number> as done.\n"
                + "- unmark <task-number>: Marks task at index <task-number> as not done.\n"
                + "- bye: exits the program.");
    }
}
