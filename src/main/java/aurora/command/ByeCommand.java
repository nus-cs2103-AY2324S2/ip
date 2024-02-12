package aurora.command;

import aurora.ui.Ui;

/**
 * The ByeCommand class handles the "bye" command.
 */
public class ByeCommand extends Command {

    /** Ui to interact with. */
    private Ui ui;

    /**
     * Constructor for the ByeCommand class.
     *
     * @param ui Ui to interact with.
     */
    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public String handle() {
        return this.ui.getExitMessage();
    }

    @Override
    public boolean isBye() {
        return true;
    }

}
