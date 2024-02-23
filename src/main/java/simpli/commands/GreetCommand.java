package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.ui.Ui;

/**
 * Chatbot will show a greeting message.
 */
public class GreetCommand implements Command {
    /** Handles UI messages that is to be displayed to user. */
    private final Ui ui;

    /**
     * Constructs the GreetCommand with the specified fields.
     *
     * @param ui which handles display messages.
     */
    public GreetCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public CommandResult execute(String[] tokens) {

        return new CommandResult(
                CommandWord.GREET,
                ui.getGreetingMessage()
        );
    }
}
