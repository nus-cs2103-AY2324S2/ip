package sleepy.tasks;

/**
 * This class is a type of task.
 *
 * @author kjw142857
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class.
     *
     * @param rawDescription Full description of the ToDo as per what the user typed in.
     * @param description Description of the ToDo, excluding the task label.
     */
    public ToDo(String rawDescription, String description) {
        super(rawDescription, description);
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
}
