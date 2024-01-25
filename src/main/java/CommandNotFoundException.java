public class CommandNotFoundException extends InputException {
    public CommandNotFoundException(String command) {
        super("Command not found: " + command);
    }
}