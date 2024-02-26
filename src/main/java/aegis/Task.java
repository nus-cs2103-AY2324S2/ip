package aegis;

import java.util.HashSet;

/**
 * Task class represents a basic type of Task with no particular specialized fields.
 * Used as a superclass that more specialized task types can inherit from.
 */
public class Task {
    protected String description;
    protected Boolean isDone;
    protected HashSet<String> tags;

    /**
     * Constructor for creating a Task object.
     *
     * @param description Description for the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<String>();
    }

    /**
     * Returns a string that contains details of the task.
     *
     * @return Task as a string containing the task details.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " " + this.getTags();
    }

    /**
     * Returns a string that contains details of the task in a format for saving to file.
     *
     * @return Task as a string formatted for saving to file.
     */
    public String toTaskSaveString() {
        return this.getStatusInt() + "|" + this.description + "|" + this.getTags();
    }

    /**
     * Returns an int representing the completion status of the task.
     * 1 if task is completed, 0 if not completed.
     *
     * @return Integer representing task completion status.
     */
    public int getStatusInt() {
        return this.isDone? 1 : 0;
    }

    /**
     * Returns a string that contains an icon representing the completion status of the task.
     * Used for printing the completion status in a more readable format.
     *
     * @return String representing task completion status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Sets the completion status of the task to completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the completion status of the task to uncompleted.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a boolean that specifies whether the task description contains the keyword.
     *
     * @param keyword Keyword to check for in task description.
     */
    public boolean checkDescription(String keyword) {
        assert keyword != null : "Keyword to search for should not be null";
        return description.contains(keyword);
    }

    /**
     * Adds a new tag to the tags of the Task.
     *
     * @param tag Tag to be added.
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Returns a string containing all the tags of the task.
     *
     * @return String containing all tags of task.
     */
    public String getTags() {
        String result = "";
        for (String tag : this.tags) {
            result += tag;
        }
        return result;
    }
}