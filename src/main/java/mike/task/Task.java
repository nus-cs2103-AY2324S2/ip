package mike.task;

import mike.ListView;
import mike.ListViewType;
import mike.MikeException;

import java.util.ArrayList;

public abstract class Task {
    private final String description;
    private boolean isDone;
    private final String type;
    protected final ArrayList<ListViewType> tags;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.tags = new ArrayList<ListViewType>();
        tags.add(ListViewType.NONE);
    }

    public boolean in(ListView listView) {
        return tags.contains(listView.getType());
    };

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String getIsDone() {
        return isDone ? "true" : "false";
    }

    public String getType() {
        return type;
    }

    public String getFileEncoding() {
        return getType() + "," + getDescription() + "," + getIsDone();
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
