package numerator.task;

import java.util.Collection;

/**
 * Represents a task that is to be done.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo with the specified description.
     *
     * @param description should contain information about the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo with the specified description and tags.
     *
     * @param description should contain information about the task.
     * @param tags        should contain information about the tags.
     */
    public ToDo(String description, Collection<String> tags) {
        this(description);
        super.addTags(tags);
    }

    /**
     * Constructs a ToDo with the specified description, tags and isDone.
     *
     * @param description should contain information about the task.
     * @param isDone      whether the task is done.
     * @param tags        should contain information about the tags.
     */
    public ToDo(String description, boolean isDone, Collection<String> tags) {
        super(description, isDone);
        super.addTags(tags);
    }

    /**
     * Returns a string representation of the task to be saved in storage.
     *
     * @return a string to be saved in storage.
     */


    @Override
    public String toString() {
        return String.format("[T][%s] %s %s",
                this.getStatusIcon(),
                this.description,
                super.getTagsString()
        ).strip();
    }

    @Override
    public String getSaveString() {
        return String.format("T | %d | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                super.getTagsSaveString()
        );
    }


}
