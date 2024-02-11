package tsundere.task;

import tsundere.exception.GeneralException;
import java.util.ArrayList;

/**
 * Encapsulates a Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> taglist;

    /**
     * Initializes Task with its name.
     * Completion status is false by default.
     *
     * @param description Name of Task.
     */
    public Task(String description) {
        assert description != null : "description should not be null";
        this.description = description;
        this.isDone = false;
        this.taglist = new ArrayList<>();
    }

    /**
     * Adds a new tag to taglist.
     *
     * @param tag Name of tag to be added.
     * @throws GeneralException if tag is not unique or taglist is full.
     */
    public void tagTask(String tag) throws GeneralException {
        if (taglist.size() > 2) {
            throw new GeneralException("Too many tags! Try untagging some first!");
        }
        if (taglist.contains(tag)) {
            throw new GeneralException("You already have this tag!");
        }
        taglist.add(tag);
    }

    /**
     * Removes a tag from taglist.
     *
     * @param tag Name of tag to be removed.
     * @throws GeneralException if tag is not found or taglist is empty.
     */
    public void untagTask(String tag) throws GeneralException {
        if (taglist.isEmpty()) {
            throw new GeneralException("No tags found! Try tagging some first!");
        }
        if (!taglist.contains(tag)) {
            throw new GeneralException("Tag not found!");
        }
        taglist.remove(tag);
    }

    /**
     * Returns completion status of Task.
     *
     * @return X if Task is done, blank otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets Task completion status to true.
     */
    public void markTaskAsDone() {
        this.isDone = true;
    }

    /**
     * Sets Task completion status to false.
     */
    public void unMarkTask() {
        this.isDone = false;
    }

    /**
     * Returns formatted String for Task storage purposes.
     *
     * @return formatted saveString.
     */
    public String toSaveString() {
        int x = this.isDone ? 1 : 0;
        return x + "," + this.description;
    }

    /**
     * Returns formatted String for tag storage purposes
     *
     * @return formatted TagString
     */
    public String getTagString() {
        StringBuilder str = new StringBuilder();
        for (String tag : taglist) {
            str.append(tag).append(" ");
        }
        return str.toString();
    }

    /**
     * Returns String representation of Task.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        StringBuilder tags = new StringBuilder();
        for (String tag : taglist) {
            tags.append(" ").append("#").append(tag);
        }
        return "[" + this.getStatusIcon() + "] " + this.description + tags;
    }

}