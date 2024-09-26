package aurora.command;

import aurora.ui.Ui;

/** The ByeCommand class represents the "bye" command. */
public class ByeCommand extends Command {

    /** Ui to interact with. */
    private Ui ui;

    /**
     * Constructs a ByeCommand object.
     *
     * @param ui Ui to interact with.
     */
    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public String handle() {
        return Ui.getExitMessage();
    }

    /**
     * Returns true since all objects of the ByeCommand class are ByeCommands.
     *
     * @return True by default.
     */
    @Override
    public boolean isByeCommand() {
        return true;
    }

}
