package rick.logic.command;

/**
 * A command that says goodbye.
 */
public class ByeCommand implements Command {
    @Override
    public String[] respond() {
        return new String[]{"Bye! See you next time."};
    }
}
