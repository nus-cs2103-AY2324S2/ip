package toothless.task;

import java.util.ArrayList;

import toothless.exception.ToothlessException;

/**
 * Abstract class to represent something to be done.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private ArrayList<Tag> tags;

    /**
     * A public constructor to initialize a new task.
     *
     * @param description A String to describe the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * A public constructor to initialize a task with an isDone value.
     *
     * @param description A String to describe the task.
     * @param isDone A Boolean to describe if the task is done.
     */
    public Task(String description, boolean isDone, ArrayList<Tag> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Returns the done status icon of the task.
     *
     * @return The String indicating X if done, and nothing if not.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns a binary of the done status of the task.
     *
     * @return The String indicating 1 if done, and 0 if not.
     */
    public String getStatusBinary() {
        return (this.isDone ? "1" : "0");
    }

    /**
     * Returns size of tag list.
     *
     * @return Integer of size of tag list.
     */
    public int getTagListSize() {
        return this.tags.size();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Adds a new Tag to tags list.
     *
     * @param label String label of tag to be added.
     * @throws ToothlessException if duplicate tag.
     */
    public void addTag(String label) throws ToothlessException {
        Tag newTag = new Tag(label);
        if (tags.contains(newTag)) {
            throw new ToothlessException("This task already has the tag " + label + ".");
        }
        tags.add(newTag);
    }

    /**
     * Deletes a tag from tags list.
     *
     * @param label String label of tag to be deleted.
     * @throws ToothlessException if tag not in list.
     */
    public void unTag(String label) throws ToothlessException {
        Tag toDeleteTag = new Tag(label);
        if (tags.indexOf(toDeleteTag) == -1) {
            throw new ToothlessException("This task does not have the tag " + label + ".");
        }
        tags.remove(toDeleteTag);
    }

    /**
     * Gets string of all tags of this task.
     *
     * @return String of all tags.
     */
    public String getTagsString() {
        String result = "Tags: ";
        int tagListSize = this.getTagListSize();
        if (tagListSize == 0) {
            result += "NIL";
        } else {
            for (int i = 0; i < tagListSize; i++) {
                result += this.tags.get(i);
                if (i != tagListSize - 1) {
                    result += ", ";
                }
            }
        }
        return result;
    }

    /**
     * Gets string of all tags of this task for storage.
     *
     * @return String of all tags for storage.
     */
    public String getTagsStorageString() {
        String result = "";
        int tagListSize = this.getTagListSize();
        if (tagListSize == 0) {
            result = "NIL";
        } else {
            for (int i = 0; i < tagListSize; i++) {
                result += this.tags.get(i).getLabel();
                if (i != tagListSize - 1) {
                    result += ", ";
                }
            }
        }
        return result;
    }

    /**
     * Returns a String representing the task formatted for storage in data file.
     *
     * @return String representing the task formatted for storage in data file.
     */
    public String toStorageString() {
        return String.format("%s | %s | %s", this.getStatusBinary(), this.description, this.getTagsStorageString());
    }

    /**
     * Returns a String representing the task formatted for printing.
     *
     * @return String representing the task formatted for printing.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
