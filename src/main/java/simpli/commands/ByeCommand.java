package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.ui.Ui;

/**
 * Chatbot will show a goodbye message.
 */
public class ByeCommand implements Command {
    /** Handles UI messages that is to be displayed to user. */
    private final Ui ui;

    /**
     * Constructs the ByeCommand with the specified fields.
     *
     * @param ui which handles display messages.
     */
    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public CommandResult execute(String[] tokens) {
        return new CommandResult(
                CommandWord.BYE,
                ui.getByeMessage()
        );
    }
}
