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
    public void execute() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
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
