public class InvalidCommandException extends CheckbotException {
    public InvalidCommandException(String cmd) {
        super("Sorry, I'm not sure what \"" + cmd + "\" means.");
    }
}
