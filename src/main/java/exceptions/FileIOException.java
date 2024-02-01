package exceptions;

public class FileIOException extends Exception {
    public FileIOException(String str) {
        super("File I/O error " + str);
    }
}