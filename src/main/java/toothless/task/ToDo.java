package toothless.task;

/**
 * A simple task with only a description.
 */
public class ToDo extends Task {
    /**
     * A public constructor to initialize a new ToDo.
     * @param description A String to describe the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * A public constructor to initialize a ToDo with an isDone value.
     * @param description A String to describe the task.
     * @param isDone A Boolean to describe if the task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toStorageString() {
        return "T | " + super.toStorageString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
