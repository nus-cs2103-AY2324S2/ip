package dylanbot;

import java.util.ArrayList;

/**
 * Represents a general Task
 */
public abstract class Task {

    private String type;
    private String desc;
    private boolean isCompleted;
    private ArrayList<String> tags;
    private int numOfTags;

    /**
     * Creates a new Task with the given type and description
     *
     * @param type The specified type
     * @param desc The specified description
     */
    public Task(String type, String desc) {
        this.type = type;
        this.desc = desc;
        this.isCompleted = false;
        this.tags = new ArrayList<>();
        this.numOfTags = 0;
    }

    /**
     * Creates a new Task with the given type, description and tags
     *
     * @param type The specified type
     * @param desc The specified description
     * @param tags The specified tags
     */
    public Task(String type, String desc, ArrayList<String> tags) {
        this.type = type;
        this.desc = desc;
        this.isCompleted = false;
        this.tags = tags;
        this.numOfTags = tags.size();
    }

    public String getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean checkCompleted() {
        return this.isCompleted;
    }

    public String getTags() {
        StringBuilder tagString = new StringBuilder();
        if (tags.isEmpty()) {
            tagString.append("none");
        }
        for (String tag : this.tags) {
            tagString.append("#").append(tag).append(" ");
        }
        return tagString.toString();
    }

    public int getNumOfTags() {
        return this.numOfTags;
    }


    /**
     * Marks the task as completed
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Adds a tag to the task
     *
     * @param tag The specified tag
     */
    public void addTag(String tag) {
        this.tags.add(tag);
        this.numOfTags++;
    }

    public abstract String toString();
}

