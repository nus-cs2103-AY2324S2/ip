public class CorruptedFileException extends PandaException {
    public CorruptedFileException() {
        super("Storage File in the wrong format. The list has been emptied.");
    }
}
