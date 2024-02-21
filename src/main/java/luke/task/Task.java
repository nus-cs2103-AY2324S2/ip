package luke.task;

import luke.Ui;

/**
 * A class that extracts the common attributes of all Tasks, such as ToDo, Deadline and Event.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a Task with a description. By default, it is not done.
     * @param description The description of the task itself.
     */
    public Task(String description) {
        assert description != null;
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void setToDone() {
        isDone = true;
    }

    /**
     * Marks itself.
     * @param ui The ui that confirms to the user about the action.
     */
    public String mark(Ui ui) {
        assert ui != null;
        isDone = true;
        return ui.displayTaskMarked(this);
    }

    /**
     * Unmarks itself.
     * @param ui The ui that confirms to the user about the action.
     */
    public String unmark(Ui ui) {
        assert ui != null;
        isDone = false;
        return ui.displayTaskUnmarked(this);
    }

    public boolean hasKeywords(String keywords) {
        return description.contains(keywords);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
