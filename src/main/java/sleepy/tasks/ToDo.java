package sleepy.tasks;

/**
 * This class is a type of task.
 *
 * @author kjw142857
 */
public class ToDo extends Task {
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
