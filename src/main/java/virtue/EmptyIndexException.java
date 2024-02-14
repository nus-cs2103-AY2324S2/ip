package virtue;

public class EmptyIndexException extends VirtueException{
    public EmptyIndexException(String type) {
        super("OOPS!!! The description of a " + type + " command cannot be empty.");
    }
}
