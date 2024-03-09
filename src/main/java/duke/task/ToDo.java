package duke.task;

/**
 * The task with only name.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
