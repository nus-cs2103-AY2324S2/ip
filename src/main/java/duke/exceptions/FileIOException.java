package duke.exceptions;

/**
 * represents an error
 * related to file input/output operations within application.
 * It extends the DuckException class, provides a specific error message
 * related to the file I/O issue.
 */
public class FileIOException extends Exception {
    public FileIOException(String str) {
        super("File I/O error " + str);
    }
}