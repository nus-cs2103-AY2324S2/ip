public class InvalidCommandException extends BobException {
    public InvalidCommandException() {
        super(Replies.INVALID_COMMAND);
    }
}
