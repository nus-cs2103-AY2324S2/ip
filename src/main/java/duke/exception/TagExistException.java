package duke.exception;

/**
 * Tag Exist Exception
 */
public class TagExistException extends DukeException {
    private String tagName;

    /**
     * Constructor
     * @param tagName existed tag
     */
    public TagExistException(String tagName) {
        super();
        this.tagName = tagName;
    }

    /**
     * Print error message
     * @return error message
     */
    @Override
    public String getMessage() {
        return String.format("%s the tag #%s is already exist", super.getMessage(), tagName);
    }
}
