package mike.task;

import java.util.ArrayList;

import mike.ListView;
import mike.ListViewType;

/**
 * Base class for all tasks.
 * Represents common attributes shared among different types of tasks.
 * @author ningc
 */
public abstract class Task {
    protected final String description;
    protected final ArrayList<ListViewType> tags;
    private final String type;
    private boolean isDone;

    /**
     * Constructor.
     * @param description What the task does.
     * @param type The type of the task.
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.tags = new ArrayList<ListViewType>();
        tags.add(ListViewType.NONE);
        tags.add(ListViewType.DESCRIPTION);
    }

    /**
     * Check if the event should be in the list view.
     * @param listView View of the list visible to user.
     * @return True if event should be in list view, otherwise false.
     */
    public boolean inListView(ListView listView) {
        return tags.contains(listView.getType()) && listView.keywordFilter(description);
    }

    /**
     * Mark a task as done.
     */
    public String markAsDone() {
        String response = this.isDone ? "This task is already done:" : "Nice! I've marked this task as done:";
        this.isDone = true;
        return response + "\n" + this;
    }

    /**
     * Mark a task as not done.
     */
    public String markAsNotDone() {
        String response = this.isDone ? "I've marked this task as not done:" : "This task was never done:";
        this.isDone = false;
        return response + "\n" + this;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    private String getDescription() {
        return description;
    }

    /**
     * Getter.
     * @return string representation of Task status.
     */
    public String getIsDone() {
        return isDone ? "true" : "false";
    }

    /**
     * Getter.
     * @return string representation of Task type.
     */
    public String getType() {
        return type;
    }

    /**
     * Getter.
     * @return The encoding of Task that is written to file.
     */
    public String getFileEncoding() {
        return getType() + "," + getDescription() + "," + getIsDone();
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
