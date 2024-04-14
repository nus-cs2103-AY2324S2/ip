package duke.task;

import java.util.ArrayList;

/**
 * Represents a task the user wants to be reminded of.
 */
public class Task {
    private String taskName;
    private boolean isDone;
    private ArrayList<String> tags;

    /**
     * Constructs a task object.
     *
     * @param taskName Name of task.
     * @param isDone Completion of task.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.tags = new ArrayList<>();
    }

    public String addTag(String tag) {
        this.tags.add(tag);
        return this.toString();
    }

    public String tagToString() {
        StringBuilder result = new StringBuilder();
        for (String t : tags) {
            if (!t.isEmpty()) {
                result.append(" #").append(t);
            }
        }
        return result.toString();
    }

    /**
     * Retrieves task name.
     *
     * @return Task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task with '|' as a splitter between each attribute.
     * String format for data storage.
     *
     * @return String representation of task for data storage.
     */
    public String toFileString() {
        return this.taskName + "|" + this.isDone;
    }

    @Override
    public String toString() {
        return this.isDone
            ? "[X] " + this.taskName
            : "[ ] " + this.taskName;
    }
}
