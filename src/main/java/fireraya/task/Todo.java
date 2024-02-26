package fireraya.task;


/**
 * This class holds a Todo task.
 *
 * It is extended from the parent class Task.
 */
public class Todo extends Task {

    /**
     * Constructor for a todo task.
     *
     * @param description description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the format to save the file on local device.
     *
     * @return string of the saved format of the task.
     */
    @Override
    public String saveFormat() {
        return String.format("T|%d|%s", isDone ? 1 : 0, description);
    }

    /**
     * Overrides a string with information about this task.
     *
     * @return string format of this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
