/**
 * class that represents when a user inputs a non-number as an index to be
 * marked/unmarked/deleted from the aaronbot tasklist
 */
public class MarkNonNumberException extends IndexErrorException{
    public MarkNonNumberException(String e) {
        super(e);
    }
}
