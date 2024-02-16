package tyler.task;

/**
 * The task class which has a basic information which is name.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructor of Task.
     *
     * @param name Name of Task.
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * This method is used by execute method in all command class. Marked the task
     * to be done and show the line that task is marked.
     */
    public String mark() {
        this.isDone = true;
        return "    Nice! I've marked this task as done:\n"
                + "    " + this;
    }

    /**
     * This method is used by execute method in all command class. Unmarked the task
     * to be not done and show the line that task is unmarked.
     */
    public String unmark() {
        this.isDone = false;
        return "    OK, I've marked this task as not done yet:\n"
                + "    " + this;
    }

    /**
     * Used by String representation of the task. If marked then return a X, else
     * return a space.
     *
     * @return X if marked, space if unmarked.
     */
    public String getIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * This method is used by storage when the storage need to save file to local.
     *
     * @return String representation of this task to be saved to the local.
     */
    public String saveToFileString() {
        int doned = this.isDone ? 1 : 0;
        return doned + " | " + this.name;
    }

    @Override
    public String toString() {
        return "[" + this.getIcon() + "] " + this.name;
    }
}
