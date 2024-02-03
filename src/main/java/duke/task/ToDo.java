package duke.task;

/**
 * Tasks to be done without any time reminder.
 */
public class ToDo extends Task {
    /**
     * Constructor for to-do object.
     *
     * @param taskName Name of task.
     * @param isDone Completion of task.
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String storeData() {
        return "todo|" + super.storeData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
