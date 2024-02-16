package lamball.task;

/**
 * Task class, the base unit of the task list.
 *
 * @author ongzhili
 */
public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructor for Task.
     *
     * @param description Name of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns task in command format for chatbot use.
     *
     * @return Task represented in command form.
     */
    public String command() {
        return "How did you get here?";
    }
    /**
     * Returns string representation of task for list printing
     *
     * @return String task represented in printing format
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Checks if the task description contains the given string.
     *
     * @param compare String used for comparison.
     * @return Whether the given string is a sub-sequence of the task.
     */
    public boolean containing(String compare) {
        return description.contains(compare);
    }
}
