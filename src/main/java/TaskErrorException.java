/**
 * class that represents an eception when a user inputs an incorrect task to be
 * added to the tasklist of aaronbot
 */
public class TaskErrorException extends Exception{

    public TaskErrorException(String e) {
        super(e);
    }
    
}
