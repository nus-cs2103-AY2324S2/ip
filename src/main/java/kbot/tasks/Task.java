package kbot.tasks;

import java.util.ArrayList;

/**
 * Encapsulate a Task with a name and status of boolean to represent whether it
 * has been completed.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Task {
    /** A String value that states the name of the Task. */
    private String name;

    /** A String value that represent the type of Task. */
    private String type;

    /** A boolean value that states whether the Task has been completed or not. */
    private boolean isCompleted;

    /** An array of tags to identify the task */
    private ArrayList<String> tags;

    /**
     * Constructor for the Task.
     * 
     * @param name        A String value that states the name of the Task.
     * @param type        Type of task.
     * @param isCompleted Whether the task has been completed.
     * @param tags        A list of tags to identify the task.
     */
    public Task(String name, String type, boolean isCompleted, ArrayList<String> tags) {
        this.name = name;
        this.type = type;
        this.isCompleted = isCompleted;
        this.tags = tags;
    }

    /**
     * Sets the status of completed to true.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Sets the status of completed to false.
     */
    public void setNotCompleted() {
        this.isCompleted = false;
    }

    /**
     * Searches for a key in name.
     * 
     * @param key String to find in the name.
     * @return Boolean if key is found or not.
     */
    public boolean containsWord(String key) {
        String[] words = this.name.split(" ");
        for (String s : words) {
            if (s.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts the data here into a format to be stored in the file.
     * 
     * @return String representation of the Task to be store in local disk.
     */
    public String convertToStorageFormat() {
        String mark = isCompleted ? "X" : " ";
        return this.type + " | " + mark + " | " + this.name;
    }

    /**
     * Transforms tags to the correct storage format.
     * 
     * @return String to store the tags.
     */
    public String tagsToStorageFormat() {
        StringBuilder tagsStorage = new StringBuilder();
        for (String tag : tags) {
            // Append the current element followed by a space
            tagsStorage.append(tag).append(" ");
        }
        return tagsStorage.toString().trim();
    }

    public ArrayList<String> getTags() {
        return this.tags;
    }

    /**
     * String representation of a Task.
     * 
     * @return Returns the String representation of a Task.
     */
    @Override
    public String toString() {
        String mark = isCompleted ? "X" : " ";
        return "[" + this.type + "]" + "[" + mark + "]" + " " + this.name;
    }
}
