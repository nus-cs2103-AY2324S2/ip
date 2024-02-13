package toothless.task;

/**
 * A Tag class to be assigned to Tasks.
 */
public class Tag {
    private String label;

    /**
     * A public constructor to initialise a Tag.
     * @param label String of label of tag.
     */
    public Tag(String label) {
        this.label = label;
    }

    /**
     * Gets the label of the Tag.
     * @return String label of Tag.
     */
    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return "#" + this.label;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Tag) {
            Tag tag = (Tag) obj;
            return this.getLabel().equals(tag.getLabel());
        }
        return false;
    }
}
