package oak.task.model;

/**
 * The Todo Class to handle all Todo-type Tasks
 */
public class Todo extends Task {
    /** The icon this task is represented by */
    public static final String ICON_TODO = "T";

    /**
     * Constructor method for Todo from user input (without the isCompleted)
     *
     * @param name
     */
    public Todo(String name) {
        super(name, ICON_TODO);
    }

    /**
     * Constructor method for Todo from tasklist.txt
     *
     * @param name
     * @param isCompleted
     */
    public Todo(String name, Boolean isCompleted) {
        super(name, ICON_TODO);

        if (isCompleted) {
            super.markTaskCompleted();
        }
    }

    @Override
    public String toTaskListStringFormat() {
        return String.format("%s|%s",
                ICON_TODO, super.toTaskListStringFormat());
    }

    @Override
    public String getTypeIcon() {
        return ICON_TODO;
    }
}
