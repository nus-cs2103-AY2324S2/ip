package chatbro;

/**
 * ToDo class that represents a chatbro.Task without any associated time.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo class.
     *
     * @param description Description of ToDo object.
     */
    public ToDo(String description) {
        super(description);
        type = "T";
    }

    /**
     * Overloaded constructor for loading from file, with extra 'isDone' parameter.
     * @param description Description of ToDo object.
     * @param isDone Boolean indicating whether the task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        type = "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toStorageFormat() {
        return super.toStorageFormat();
    }
}
