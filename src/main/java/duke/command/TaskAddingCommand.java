package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.helpers.FileManaging;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;


/**
 * AddCommand Class
 */
public class TaskAddingCommand extends Command {

    private String[] commandArr;

    /**
     * Constructor of Addcommand class.
     *
     * @param commandArr Array of commands.
     */
    public TaskAddingCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage, Storage secondaryStorage) {
        try {
            String output = tasks.addTask(commandArr[0], commandArr.length > 1 ? commandArr[1] : "");
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
