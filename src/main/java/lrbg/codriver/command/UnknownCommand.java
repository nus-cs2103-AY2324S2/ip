package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

public class UnknownCommand extends Command {
    private final String unknownCommand;
    public UnknownCommand(String unknownCommand) {
        this.unknownCommand = unknownCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        throw new CoDriverException("I'm sorry, but I don't understand this command: " + unknownCommand);
    }

    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof UnknownCommand) {
            UnknownCommand other = (UnknownCommand) obj;
            return other.unknownCommand.equals(this.unknownCommand);
        } else {
            return false;
        }
    }
}
