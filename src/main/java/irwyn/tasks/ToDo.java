package irwyn.tasks;

/**
 * This class encapsulates the ToDo class.
 * It represents a task todo.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo object.
     *
     * @param description The description of the todo.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
