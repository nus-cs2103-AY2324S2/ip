package iggly.command;

import iggly.duke.Storage;
import iggly.view.ExitView;

/**
 * Command to terminate the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private final ExitView exitView;

    /**
     * Constructs an {@code ExitCommand}.
     */
    public ExitCommand() {
        this.exitView = new ExitView();
    }

    /**
     * Executes the {@link ExitCommand}, print exit message when the program is terminated.
     * <p>
     * This method display the exit message by initialising the exit view.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@link ExitCommand}
     */
    @Override
    public String execute(Storage storage) {
        return exitView.display();
    }
}
