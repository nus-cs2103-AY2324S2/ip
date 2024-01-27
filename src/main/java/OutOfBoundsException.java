public class OutOfBoundsException extends PandaException {
    public OutOfBoundsException() {
        super("OOPS! The item you want to mark doesn't exist. Use the 'list' command to check your current tasks.");
    }
}
