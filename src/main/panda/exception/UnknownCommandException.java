public class UnknownCommandException extends PandaException {
    public UnknownCommandException() {
        super("OOPS! Panda doesn't have that command.");
    }
}
