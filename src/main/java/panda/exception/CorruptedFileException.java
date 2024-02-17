package panda.exception;

public class CorruptedFileException extends PandaException {
    /**
     * Constructs a new CorruptedFileException with a default message.
     */
    public CorruptedFileException() {
        super("Storage File in the wrong format. The list has been emptied.");
    }
}
