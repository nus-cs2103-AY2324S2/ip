package duke;

/**
 * Represent a subclass that stores some information to be used with a Chatbot
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Todo extends Task {


    /**
     * Constructor for a todo instance
     *
     * @param description to be used to identify a todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints Todo description in Task Array
     *
     * @return a string representing the task description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toString(boolean update) {
        return "T@" + super.toString(update);
    }

    @Override
    public String snoozeTask() throws DukeException {
        throw new DukeException("This task has no time element to snooze!");
    }
}
