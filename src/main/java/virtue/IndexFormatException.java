package virtue;

public class IndexFormatException extends VirtueException {
    public IndexFormatException(String type) {
        super("The index of a " + type + " command must be an integer.");
    }
}
