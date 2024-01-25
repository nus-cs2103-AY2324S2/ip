public class CommandNotFoundException extends Exception{
    public CommandNotFoundException(String Command) {
        super("Error: " + Command + " is not a valid command!");
    }
}
