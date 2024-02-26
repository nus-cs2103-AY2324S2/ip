package tasks;

/**
 * Represents a task of type Todo,
 * with task description, completion status and task type as available details.
 */
public class TodoTask extends Task {
    /**
     * Constructor for TodoTask object.
     *
     * @param what description of the task
     * @param status completion status of task
     */
    public TodoTask(String what, String status) {
        super(what, status, "[T]");
    }

    /**
     * Factory method for TodoTask object
     *
     * @param str String array with task description
     * @return TodoTask object with task details in fields
     */
    public static TodoTask of(String str) {
        return new TodoTask(str, "f");
    }

    /**
     * Returns string showing task details.
     *
     * @return string of task type, marked/unmarked status, description and deadline
     */

    public String showAll() {
        return super.showAll();
    }

    /**
     * Returns TodoTask details in table row form
     *
     * @return String representation of TodoTask to be saved into txt file
     */
    @Override
    public String toString() {
        return "T / " + super.toString();
    }
}