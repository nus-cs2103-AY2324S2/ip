package duke.exceptions;

/**
 * This class implements an excpetion for when the bot recieves an invalid command
 * 
 * @author delishad21
 */
public class NoSuchCommandException extends Exception {

    /**
     * Creates a NoSuchCommandException, used when user inputs an invalid command.
     * 
     * @param command The invaild command.
     */
    public NoSuchCommandException(String command) {
        super("\"" + command + "\" is not within my capabilities");
    }

}
