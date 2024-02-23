package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.ui.Ui;

public class GreetCommand implements Command {
    private final Ui ui;

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
