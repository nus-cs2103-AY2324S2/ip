package duke.tasks;

/**
 * Class representing todo class.
 */
public class Todo extends Task {
    private static String TASK_TYPE = "[T] ";
    private static String COMPLETED_MESSAGE_END = " is complete!";
    private static String INCOMPLETE_MESSAGE_END = " has yet to be completed.";
    private static String TODO = "todo";

    /**
     * Constructor for new todo tasks.
     * @param name Description or name of the given task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor for existing todo tasks.
     * @param name Description or name of the given task.
     * @param isDone Completion status of the task.
     */
    public Todo(String name, String isDone) {
        super(name, isDone);
    }    

    @Override
    public String checkStatus() {
        if (this.checkDone()) {
            return TASK_TYPE + this.getName() + COMPLETED_MESSAGE_END;
        } else {
            return TASK_TYPE + this.getName() + INCOMPLETE_MESSAGE_END;
        }
    }

        /**
     * Returns string representing the current attributes of the todo task.
     * @return String representing the current attributes of the todo task.
     */
    public String getAttributes() {
        String isDoneString = "";
        if (this.isDone) {
            isDoneString = "true";
        } else {
            isDoneString = "false";
        }

        return TODO + " " + isDoneString + " " + this.name;
    }
}
