package command;

import data.TaskList;
import data.exception.CoDriverException;
import storage.Storage;
import ui.Ui;
public class UnknownCommand extends Command {
    private final String unknownCommand;
    public UnknownCommand(String unknownCommand) {
        this.unknownCommand = unknownCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        throw new CoDriverException("I'm sorry, but I don't understand this command: " + unknownCommand);
    }
}
