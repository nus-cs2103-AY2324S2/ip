package duke;

/**
 * Represents a tag that can be associated with a task. A tag is a simple label that helps to categorize or
 * mark tasks for easy identification or grouping.
 */
public class Tag {
    /**
     * The name of the tag.
     */
    private String tagName;

    /**
     * Constructs a new Tag with the specified name.
     *
     * @param tagName The name of the tag. This name is used to identify or categorize tasks.
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Returns the name of the tag.
     *
     * @return The name of the tag.
     */
    public String getTagName() {
        return tagName;
    }
}
