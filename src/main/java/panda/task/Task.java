package panda.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Iterator;
import java.util.HashSet;

public class Task {
    private final String desc;
    private boolean isDone;
    private HashSet<String> tags;
    
    /**
     * Constructs a new Task with the given description.
     * 
     * @param desc the description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        isDone = false;
        tags = new HashSet<>();
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Tags the task as given tag.
     * @param tag the string to tag the task
     */
    public void tag(String tag) {
        tags.add(tag);
    }

    /**
     * Untags the given tag from the task.
     * @param tag the string to untag the task
     */
    public void untag(String tag) {
        tags.remove(tag);
    }

    /**
     * Returns the current status of the task.
     * 
     * @return the current status of the task.
     */
    private String curStatus() {
        return "[" + (isDone ? 'X' : ' ') + "]";
    }

    /**
     * Returns the string representation of the task.
     * 
     * @return the string representation of the task.
     */
    public String toString() {
        return curStatus() + ' ' + desc;
    }

    /**
     * Returns the string representation of the task suitable for saving to a file.
     * 
     * @return the string representation of the task.
     */
    public String toSaveString() {
        return (isDone ? "1" : "0") + " | " + desc + " " + tagToString();
    }

    /**
     * Returns the string representation of the tags
     * 
     * @return the string representation of the task.
     */
    public String tagToString() {
        // Creating an iterator 
        Iterator<String> value = tags.iterator(); 
  
        // The initial return string
        String tagString = "#";

        while (value.hasNext()) { 
            tagString = tagString + value.next() + " "; 
        } 
        return tagString;
    }

    /**
     * Checks if the description matches the given filter string.
     * The filter string is treated as a regular expression and the comparison is case insensitive.
     * 
     * @param fString the filter string to match against.
     * @return true if the description matches the filter string, false otherwise.
     */
    public boolean match(String fString) {
        Pattern pattern = Pattern.compile(fString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(desc);
        return matcher.find();
    }

    /**
     * Checks if the task has a matching tag
     * 
     * @param tag the filter string to match against.
     * @return true if the description matches the filter string, false otherwise.
     */
    public boolean isTagged(String tag) {
        return tags.contains(tag);
    }
    
    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }
         
        Task c = (Task) o;

        return desc.equals(c.desc) && isDone == c.isDone;
    }
}
