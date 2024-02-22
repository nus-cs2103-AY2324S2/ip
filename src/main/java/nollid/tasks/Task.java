package nollid.tasks;

import java.util.ArrayList;

/**
 * Task class represents a generic task with a description and completion status.
 */
public abstract class Task { // Adapted from partial solution provided on CS2103 website
    /**
     * The description of the task.
     */
    private String description;

    /**
     * Indicates whether the task is done or not.
     */
    private boolean isDone;

    /**
     * List of tags associated with the Task.
     */
    private ArrayList<String> tags;

    /**
     * Constructs a Task object with the specified description.
     * isDone set to false by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a Task object with the specified description and list of tags.
     * isDone set to false by default.
     *
     * @param description The description of the task.
     * @param tags        The List of tags associated with the Task.
     */
    public Task(String description, ArrayList<String> tags) {
        this.description = description;
        this.isDone = false;
        this.tags = tags;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The description as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns boolean value of isDone.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone The new completion status (true if done, false if not done).
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Overrides the toString method to provide a string representation of the Task object.
     *
     * @return A formatted string representing the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Retrieves a string representation of the tags associated with the task.
     *
     * @return A formatted string containing the task's tags, or an empty string if no tags are present.
     */
    public String getTagsString() {
        if (tags.isEmpty()) {
            return "";
        }

        StringBuilder output = new StringBuilder("\nTags: ");

        for (int i = 0; i < tags.size(); i++) {
            if (i == tags.size() - 1) {
                output.append(tags.get(i));
            } else {
                output.append(tags.get(i))
                        .append(", ");
            }
        }

        return output.toString();
    }

    /**
     * Retrieves the list of tags associated with the task.
     */
    public ArrayList<String> getTags() {
        return this.tags;
    }
}
