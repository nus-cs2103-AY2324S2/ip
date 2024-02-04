package duke.exception;

public class TagNotExistException extends DukeException {
    private String tagName;

    public TagNotExistException(String tagName) {
        super();
        this.tagName = tagName;
    }

    @Override
    public String getMessage() {
        return String.format("%s the tag #%s does not exist", super.getMessage(), tagName);
    }
}
