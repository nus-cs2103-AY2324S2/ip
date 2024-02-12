package Aaron.Exception;
/**
 * Class that represents an error when duplicate tasks are being added
 */
public class DuplicateTaskException extends TaskErrorException{
    public DuplicateTaskException(String e) {
        super(e);
    }
}
