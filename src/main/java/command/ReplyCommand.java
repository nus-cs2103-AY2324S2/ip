package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class ReplyCommand extends Command {
    private String customMessage;

    /**
     * Constructor for Reply Command.
     *
     * @param customMessage message to be returned.
     * */
    public ReplyCommand(String customMessage) {
        this.customMessage = customMessage;
    }

    /**
     * Returns the custom message as a String.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return customMessage;
    }

    /**
     * Informs if this command is an Exit command.
     *
     * @return Boolean value of true if this command is an exit command.
     * */
    @Override
    public boolean isExit() {
        return false;
    }
}
