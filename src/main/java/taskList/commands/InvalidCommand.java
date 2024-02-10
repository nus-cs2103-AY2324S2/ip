package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

/** Represents invalid commands. Implements the Command interface. */
public class InvalidCommand implements Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Implementation for handling the execution of an invalid command
        ui.showErrorMessage(errorMessage);
    }
}
