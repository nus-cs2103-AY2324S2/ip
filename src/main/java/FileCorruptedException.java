public class FileCorruptedException extends DukeException{
    public FileCorruptedException(String displayMessage) {
        super("File data may be corrupted", displayMessage);
    }
}
