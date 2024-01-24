package command;

import duke.Storage;
import view.ExitView;

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
     * Executes the {@code ExitCommand}, print exit message when the program is terminated.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@code ExitCommand}
     */
    @Override
    public void execute(Storage storage) {
        exitView.display();
    }
}
