package lucky.tasks;

import java.util.HashSet;

/**
 * The Task class represents a task with a description and a status, and provides methods to set and
 * get the status, convert the task to a string representation, and convert the task to a storage
 * string.
 */
public class Task {
    protected final String description;

    private boolean isMarked;

    private HashSet<String> tags;

    /**
     * Defines a constructor for the `Task` class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.tags = new HashSet<>();
    }

    public void setMarked(boolean status) {
        this.isMarked = status;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsMarked() {
        return this.isMarked;
    }

    public HashSet<String> getTags() {
        return this.tags;
    }

    /**
     * Adds tag(s) to a task. Duplicate tags are ignored on addition.
     *
     * @param tagDetails Array which contains the #tags of the task.
     */
    public void addTags(String[] tagDetails) {
        for (String tag : tagDetails) {
            this.tags.add(tag);
        }
    }

    /**
     * Removes tag(s) from a task. Non-existent tags are ignored.
     *
     * @param tags Array of strings which contains the tags to remove.
     */
    public void removeTags(String[] tags) {
        for (String tag : tags) {
            this.tags.remove(tag);
        }
    }

    /**
     * Converts a task object to its string representation for storage.
     *
     * @return Empty string.
     */
    public String toStorageString() {
        return "";
    }

    /**
     * Returns a string representation of a task with its status and description.
     *
     * @return A string representation of a task.
     */
    @Override
    public String toString() {
        String checkBox = "[ ]";

        if (isMarked) {
            checkBox = "[X]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(checkBox).append(" ").append(description);

        for (String tag : tags) {
            sb.append(" ").append(tag);
        }

        return sb.toString();
    }
}
