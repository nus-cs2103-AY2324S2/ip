package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import duke.exception.DukeException;

/**
 * A class for managing all kinds of Tasks.
 */
public abstract class Task implements Comparable<Task> {
    /** The task list, update upon creation of task */
    protected String description;
    protected boolean isDone;
    protected HashSet<String> tags;

    /**
     * Constructor
     *
     * @param text the description of the task
     */
    public Task(String text) {
        this.description = text;
        this.isDone = false;
        this.tags = new HashSet<String>();
    }

    /**
     * Constructors with tag
     *
     * @param text description
     * @param tags tags
     */
    public Task(String text, ArrayList<String> tags) {
        this.description = text;
        this.tags = new HashSet<>(tags);
        this.isDone = false;
    }

    /**
     * @return if task is Done, we cross it out.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task with X
    }

    /**
     * @return if task is Done, return true.
     */
    public Boolean getStatus() {
        return isDone;
    }

    /**
     * Return the description of task.
     * @return return the description of task.
     */
    public String getDescription() {
        assert this.description.length() > 0 : "at least some description should be given";
        return this.description;
    }

    /**
     * abstract method
     * @return return T/D/E.
     */
    public abstract String getTaskTypeIcon();

    /**
     * abstract method
     * @return return todo/event/deadline.
     */
    public abstract String getTaskType();

    /**
     * Whether we have to start doing it
     * @param current current time
     * @return yes/no
     */
    public abstract boolean isTimeForStart(LocalDate current);

    /**
     * Change the status of task from not Done to Done
     */
    public void setDone() {
        assert !this.isDone : "task should not be done before we mark it done.";
        this.isDone = true;
    }

    /**
     * Change the status of task from not Done to Done
     */
    public void unDone() {
        assert this.isDone : "task should be done before we mark it undone.";
        this.isDone = false;
    }

    /**
     * Add a tag
     * @param tagName tag name
     * @return whether the tag already exist
     */
    public boolean addTag(String tagName) {
        return this.tags.add(tagName);
    }

    /**
     * Remove a tag
     * @param tagName tag name
     * @return whether the tag not exist
     */
    public boolean removeTag(String tagName) {
        return this.tags.remove(tagName);
    }

    /**
     * Print tags
     * @return String representation of tags
     */
    public String printTags() {
        Iterator<String> tagIterator = tags.iterator();
        String result = "";
        while (tagIterator.hasNext()) {
            result += "#" + tagIterator.next();
        }
        return result;
    }

    /**
     * To string method.
     * @return the String representation of duke.task.Task
     */
    @Override
    public String toString() {
        String temp = String.format("[%s][%s] [%s] %s",
                this.getTaskTypeIcon(), this.getStatusIcon(), this.printTags(), this.description);
        return temp;
    }

    @Override
    public abstract int compareTo(Task otherTask);

    public abstract void updateTask(String updateField, String updateValue) throws DukeException;
}
