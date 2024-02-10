package ChatBro;

/**
 * Exception for wrong file format when loading from savedtasks.txt.
 */
public class WrongFileFormatException extends Exception {
    public WrongFileFormatException(String message) {
        super(message);
    }

}
