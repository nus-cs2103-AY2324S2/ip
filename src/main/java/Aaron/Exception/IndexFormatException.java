package aaron.exception;

/**
 * class that represents when a user inputs a non-number as an index to be
 * marked/unmarked/deleted from the aaronbot tasklist
 */
public class IndexFormatException extends IndexErrorException {
    public IndexFormatException(String e) {
        super(e);
    }
}
