package friendlytool.task;


/**
 * Class for managing ToDo tasks.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task.
     *
     * @param name   name of the task
     * @param isDone completed or not.
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }


    /**
     * Converts a todo task to a readable format.
     *
     * @return string format of a todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Converts a todo task to a save format.
     *
     * @return todo task in a save format.
     */
    @Override
    public String toSaveFormat() {
        return "T " + super.toSaveFormat() + "\n";
    }
}
