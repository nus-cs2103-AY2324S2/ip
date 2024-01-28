package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private final int indexToMark;
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