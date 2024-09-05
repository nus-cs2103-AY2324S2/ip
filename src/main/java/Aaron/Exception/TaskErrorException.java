package aaron.exception;

/**
 * class that represents an exception when a user inputs an incorrect task to be
 * added to the tasklist of aaronbot
 */
public class TaskErrorException extends AaronBotException {

    public TaskErrorException(String e) {
        super(e);
    }

}
