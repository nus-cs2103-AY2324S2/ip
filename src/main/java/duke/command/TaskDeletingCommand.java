package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.helpers.FileManaging;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * DeleteCommand class
 */
public class TaskDeletingCommand extends Command {
    private String[] commandArr;

    /**
     * Constructor of delete command.
     *
     * @param commandArr Array of commands.
     */
    public TaskDeletingCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage) {
        try {
            String output = tasks.deleteTask(commandArr.length > 1 ? commandArr[1] : "");
            FileManaging.writeToFile(CommandType.FILEPATH.toString(), tasks);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
