package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.TasksFileException;
import duke.Ui;

import java.io.IOException;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasksFileException {
        try {
            ui.close();
            storage.saveTasksFile(taskList);
            ui.goodbye();
        } catch (IOException e) {
            throw new TasksFileException();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
