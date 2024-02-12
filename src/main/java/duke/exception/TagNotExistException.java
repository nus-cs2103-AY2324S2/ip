package duke.exception;

/**
 * Tag Not Exist Exception
 */
public class TagNotExistException extends DukeException {
    private String tagName;

    /**
     * Constructor
     * @param tagName tag name
     */
    public TagNotExistException(String tagName) {
        super();
        this.tagName = tagName;
    }

    /**
     * Print error message.
     * @return error message
     */
    @Override
    public String getMessage() {
        return String.format("%s the tag #%s does not exist", super.getMessage(), tagName);
    }
}
