package duke.task;

/**
 * Represents a todo task that stores the description and status.
 */
public class ToDo extends Task {

    /**
     * Initlialises a ToDo with the description and status of completion.
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }

}
