package jivox.task;

/**
 * Tag is a class which is used to tag the Tasks with a keyword
 */
public class Tag {
    private final String content;

    public Tag(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
