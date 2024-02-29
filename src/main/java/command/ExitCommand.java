package command;

/**
 * {@inheritDocs}
 * Exits the program.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDocs}
     * Exits the program.
     */
    @Override
    public String execute() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
        return exitMessage;
    }

    /**
     * {@inheritDoc}
     *
     * @return True if program will exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
