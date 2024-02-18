package bond.task;

/**
 * Represents a to-do task in the Bond task management program.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class ToDoTask extends Task {

    /**
     * Constructor for the ToDoTask class.
     *
     * @param name The name of the to-do task.
     */
    public ToDoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
