package duke.exception;

public class TagExistException extends DukeException {
    private String tagName;

    public TagExistException(String tagName) {
        super();
        this.tagName = tagName;
    }

    @Override
    public String getMessage() {
        return String.format("%s the tag #%s is already exist", super.getMessage(), tagName);
    }
 }
