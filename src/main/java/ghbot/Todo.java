package ghbot;

/**
 * ghbot.Todo class.
 * This class contains informations about the todo.
 */
public class Todo extends Task {

    /**
     * ghbot.Todo Constructor.
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return a string description for the item in the list for file operation.
     * @return a string.
     */
    @Override
    public String toFile() {
        return "T " + super.toFile();
    }

    /**
     * Return a string description of a todo item.
     * @return a string that describe the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
