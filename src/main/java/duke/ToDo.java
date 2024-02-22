package duke;

/**
 * The ToDo class represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }

    /**
     * Creates a ToDo object from a string.
     *
     * @param input The string to parse.
     * @return A new ToDo object.
     */
    public static ToDo fromString(String input) {
        String[] split = input.split(" \\| ");
        ToDo todo = new ToDo(split[2]);
        if (split[1].equals("X")) {
            todo.markAsDone();
        }
        return todo;
    }

    public static String getHelpString() {
        return "Add Todo: Adds a to-do task to the list.\n"
                + "  To add a to-do task, use the following format\n"
                + "    todo <description>";
    }
}