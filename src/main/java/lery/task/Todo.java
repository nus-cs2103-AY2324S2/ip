package lery.task;

/**
 * Represents a todo task in the Lery chatbot application,
 * which is a specific type of task.
 *
 * It extends the generic Task class and provides methods to get the task type,
 * extra information (shortened and full), and description. Additionally, it overrides
 * the getType, getExtraInfoShortened, and getExtraInfo methods to customize the behavior
 * for a todo task.
 *
 */
public class Todo extends Task {

    private static final String TYPE = "T";

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description the description of the todo task.
     * @param isDone      the completion status of the task (true if done, false otherwise).
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the type of the todo task.
     *
     * @return the task type ("T" for todo).
     */
    @Override
    public String getType() {
        return this.TYPE;
    }


    /**
     * Gets a shortened version of extra information.
     *
     * @return an empty string.
     */
    @Override
    public String getExtraInfoShortened() {
        return "";
    }

    /**
     * Gets the extra information of the todo task.
     *
     * @return an empty string.
     */
    @Override
    public String getExtraInfo() {
        return "";
    }
}
