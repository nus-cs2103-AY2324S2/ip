package aaron.exception;

/**
 * class that represents an error relating to the index given by a user when
 * marking/unmarking/deleting a task in the aaronbot tasklist
 */
public class IndexErrorException extends AaronBotException {
    public IndexErrorException(String e) {
        super(e);
    }
}
