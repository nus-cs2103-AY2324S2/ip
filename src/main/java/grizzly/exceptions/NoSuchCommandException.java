package grizzly.exceptions;

/**
 * This class implements an excpetion for when the bot recieves an invalid command
 *
 * @author delishad21
 */
public class NoSuchCommandException extends GrizzlyException {

    /**
     * Creates a NoSuchCommandException, used when user inputs an invalid command.
     *
     * @param command The invaild command.
     */
    public NoSuchCommandException(String command) {
        super("\"" + command + "\" is not within my capabilities, type 'help' to see what I can do.");
    }

}
