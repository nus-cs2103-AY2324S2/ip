package mike.task;

import mike.ListView;
import mike.ListViewType;

import java.util.ArrayList;

/**
 * Base class for all tasks.
 * Represents common attributes shared among different types of tasks.
 * @author ningc
 */
public abstract class Task {
    private final String type;
    private boolean isDone;

    protected final String description;
    protected final ArrayList<ListViewType> tags;

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
        return tags.contains(listView.getType()) && listView.descriptionFilter(description);
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark a task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
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
