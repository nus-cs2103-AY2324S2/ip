package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to run the Mark Command.
 *
 * @author KohGuanZeh
 */
public class MarkCommand extends Command {
    private final int indexToMark;

    /**
     * Creates a Command that runs to mark a task as done at a specified index.
     *
     * @param input Input of task to mark.
     * @throws CommandException Exception when input is not given in integer format.
     */
    public MarkCommand(String input) throws CommandException {
        input = input.trim();
        try {
            this.indexToMark = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new CommandException("Error. Mark expects the index of task to be marked.");
        }
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showMessage(taskList.markTask(this.indexToMark));
        storage.save(taskList.toDataString());
    }
}
