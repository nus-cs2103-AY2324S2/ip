package waffles.tasks;

/**
 * The ToDo class represents a to-do task in the waffles chatbot application.
 * It extends the Task class.
 */
public class ToDo extends Task {

    private static final String TODO_MESSAGE = "[T]%s";
    private static final String TODO_FILE_TEMPLATE = "T | %s";

    /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task for saving to a file.
     *
     * @return The string representation of the to-do task for saving to a file.
     */
    public String toTaskFileTemplate() {
        return String.format(TODO_FILE_TEMPLATE, super.toTaskFileTemplate());
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return String.format(TODO_MESSAGE, super.toString());
    }
}
