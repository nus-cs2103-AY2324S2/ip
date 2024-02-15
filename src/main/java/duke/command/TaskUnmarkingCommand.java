package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.helpers.FileManaging;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * UnmarkCommand class
 */
public class TaskUnmarkingCommand extends Command {
    private String[] commandArr;

    /**
     * Constructor of unmark command.
     *
     * @param commandArr
     */
    public TaskUnmarkingCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage, Storage secondaryStorage) {
        try {
            String output = tasks.markUndone(commandArr.length > 1 ? commandArr[1] : "");
            FileManaging.writeToFile(CommandType.FILEPATH.toString(),
                    CommandType.SECONDARYFILEPATH.toString(), tasks);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
