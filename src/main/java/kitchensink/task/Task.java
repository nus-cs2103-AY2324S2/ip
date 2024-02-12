package kitchensink.task;

import kitchensink.Ui;

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
        isDone = true;
        return ui.sayTaskMarked(this);
    }

    /**
     * Unmarks itself.
     * @param ui The ui that confirms to the user about the action.
     */
    public String unmark(Ui ui) {
        isDone = false;
        return ui.sayTaskUnmarked(this);
    }

    public boolean hasKeywords(String keywords) {
        return description.contains(keywords);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
