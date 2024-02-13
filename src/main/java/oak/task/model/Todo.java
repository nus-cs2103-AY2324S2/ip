package oak.task.model;

/**
 * The Todo Class to handle all Todo-type Tasks
 */
public class Todo extends Task {
    /** The icon this task is represented by */
    public static String TODO_TYPEICON = "T";

    /**
     * Constructor method for Todo from user input (without the isCompleted)
     *
     * @param name
     */
    public Todo(String name) {
        super(name, TODO_TYPEICON);
    }

    /**
     * Constructor method for Todo from tasklist.txt
     *
     * @param name
     * @param isCompleted
     */
    public Todo(String name, Boolean isCompleted) {
        super(name, TODO_TYPEICON);

        if (isCompleted) {
            super.markTaskCompleted();
        }
    }

    @Override
    public String toTaskListStringFormat() {
        return String.format("%s|%s",
                TODO_TYPEICON, super.toTaskListStringFormat());
    }

    @Override
    public String getTypeIcon() {
        return TODO_TYPEICON;
    }
}
