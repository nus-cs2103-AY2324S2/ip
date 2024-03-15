package asher.tasks;

/**
 * Represents a type of task called ToDo.
 */
public class Todo extends Task {
    /**
     * Constructs a ToDo with the given description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String writeToString() {
        String status = isDone ? "1" : "0";
        return "T" + " | " + status + " | " + description;
    }

    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}
