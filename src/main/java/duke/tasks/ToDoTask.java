package duke.tasks;

/**
 * The ToDoTask class represents a task without any specific deadline.
 * It extends the Task class and inherits its attributes and methods.
 * <p>
 * A to-do task includes a description but does not have a specific deadline.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.tasks.Task
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask with the specified description.
     *
     * @param description the description of the to-do task.
     */
    public ToDoTask(String description) {
        super(description);
    }
    @Override
    public void updateDoneIcon() {
        super.updateDoneIcon();
    }

    @Override
    public String getDoneIcon() {
        return super.getDoneIcon();
    }
    @Override
    public void markDone() {
        super.markDone();
    }
    @Override
    public void markNotDone() {
        super.markNotDone();
    }

    @Override
    public String toString() {
        return "T" + " | " + super.toString();
    }


}
