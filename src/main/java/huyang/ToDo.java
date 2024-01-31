package huyang;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    /**
     * Constructor for the ToDo class.
     *
     * @param taskName The name of the to-do task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    private String getTypeIcon() {
        return "[T]";
    }

    /**
     * Converts the to-do task to a formatted string for saving to a file.
     *
     * @return A string in file format representing the to-do task.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + taskName;
    }

    /**
     * Creates a ToDo object from a string in file format.
     *
     * @param fileFormat A string in file format representing a to-do task.
     * @return A ToDo object created from the file format string.
     * @throws TaskException if the file format is invalid.
     */
    public static ToDo fromFileFormat(String fileFormat) throws TaskException {
        String[] parts = fileFormat.split(" \\| ");
        if (parts.length < 3) {
            throw TaskException.forInvalidTaskFormat("ToDo");
        }
        ToDo todo = new ToDo(parts[2]);
        if (parts[1].equals("1")) {
            todo.check();
        }
        return todo;
    }

    /**
     * Converts the to-do task to a formatted string for displaying to the user.
     *
     * @return A string representing the formatted to-do task.
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + " " + taskName;
    }
}
