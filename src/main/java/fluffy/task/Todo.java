package fluffy.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo.
     *
     * @param description The description of the todo.
     * @param isDone Whether the todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns the todo in the format to be displayed to the user.
     *
     * @return The todo in the format to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString();
    }

    /**
     * Returns the todo in the format to be saved to the file.
     *
     * @return The todo in the format to be saved to the file.
     */
    @Override
    public String toFileString() {
        return this.getType() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Creates a todo from a file string.
     *
     * @param fileString The file string to be converted to a todo.
     * @return The todo from the file string.
     */
    public static Todo todoFromFileString(String fileString) {
        String[] taskDetails = fileString.split(" \\| ");
        boolean isDone = taskDetails[1].equals("1");
        String description = taskDetails[2];
        return new Todo(description, isDone);
    }
}
