package sleepy.tasks;

/**
 * This class is a type of task.
 *
 * @author kjw142857
 */
public class ToDoTask extends Task {

    /**
     * Constructor for the ToDoTask class.
     *
     * @param description Description of the ToDoTask, excluding the task label.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns the description of this toDo.
     *
     * @return Description of this toDo.
     */
    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }

    /**
     * Returns the raw description of this toDo (as it was added by the user).
     *
     * @return Raw description of this toDo.
     */
    @Override
    public String getRawDescription() {
        String details = super.getDescription().substring(TASK_DESCRIPTION_OFFSET);
        return "todo " + details;
    }
}
