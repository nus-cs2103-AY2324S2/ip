package virtue;

public class EmptyDescriptionException extends VirtueException {
    public EmptyDescriptionException(String type) {
        super("OOPS!!! The description of a " + type + " cannot be empty.");
    }
}
