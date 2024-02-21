package rick.logic.command;

/**
 * A command that says goodbye.
 */
public class ByeCommand implements Command {
    /**
     * Returns a string array containing important arguments of a bye command.
     * @return a string containing command type and related information.
     */
    @Override
    public String[] respond() {
        return new String[]{"B", "See you later, Morty! burp Remember, don't do anything stupid while I'm gone."};
    }
}
