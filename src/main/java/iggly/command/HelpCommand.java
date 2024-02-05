package iggly.command;

import iggly.duke.Storage;
import iggly.view.HelpView;

/**
 * Command to list out all commands supported by Iggly.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    private final HelpView helpView;

    /**
     * Constructs a {@link HelpCommand}.
     * This command is used to list all commands supported by Iggly.
     *
     */
    public HelpCommand() {
        this.helpView = new HelpView();
    }

    /**
     * Executes the {@link HelpCommand}, prints the lists of {@link Command}.
     * <p>
     * This method display the list of commands available.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@link HelpCommand}
     */
    @Override
    public String execute(Storage storage) {
        return helpView.display();
    }
}
