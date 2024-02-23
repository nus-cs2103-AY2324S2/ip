package dav;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a generic task with a description and completion status.
 */
class Task {

    protected String description;
    protected boolean isDone;
    private Set<String> tags;

    /**
     * Constructs a Task with the specified description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    /**
     * Gets the status icon representing the completion status of the task.
     * @return Status icon as a string ("[X]" for completed, "[ ]" for not completed).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Gets the description representing the task.
     * @return Description of the task as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Converts the task to a string for data storage.
     * @return Formatted string for data storage.
     */
    public String toDataString() {
        return "";
    }

    public Set<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    /**
     * Parses task data string and returns a Task object.
     * @param data Data string representing the task.
     * @return Task object if parsing is successful, otherwise null.
     */
    public static Task parseTask(String data) {
        if (data.startsWith("T")) {
            return TodoTask.parseTask(data);
        } else if (data.startsWith("D")) {
            return DeadlineTask.parseTask(data);
        } else if (data.startsWith("E")) {
            return EventTask.parseTask(data);
        }

        return null;
    }

    /**
     * Converts the task to a string for display.
     * @return Formatted string for display.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getStatusIcon() + " " + description);

        if (!tags.isEmpty()) {
            result.append("\n   Tags: ");
            for (String tag : tags) {
                result.append(tag).append(", ");
            }
            result.delete(result.length() - 2, result.length());
        }

        return result.toString();
    }
}

