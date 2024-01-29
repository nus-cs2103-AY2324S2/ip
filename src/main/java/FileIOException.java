public class FileIOException extends DukeException{
    FileIOException(String str) {
        super("File I/O error " + str);
    }
}
