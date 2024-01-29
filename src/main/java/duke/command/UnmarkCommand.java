package duke.command;

import duke.exception.DukeException;
import duke.helpers.FileManaging;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private String[] commandArr;

    public UnmarkCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markUndone(commandArr.length > 1 ? commandArr[1] : "");
            FileManaging.writeToFile(CommandType.FILEPATH.toString(), tasks);
        } catch (DukeException e) {
            ui.displayToScreen(e.getMessage());
        } catch (IOException e) {
            ui.displayToScreen(e.getMessage());
        }
    }
}
