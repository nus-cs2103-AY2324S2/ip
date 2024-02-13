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
public class AddCommand extends Command {

    private String[] commandArr;

    /**
     * Constructor of Addcommand class.
     *
     * @param commandArr Array of commands.
     */
    public AddCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    /**
     * Executes add command.
     *
     * @param tasks List of tasks.
     * @param ui Ui of ai chatbot.
     * @param storage External storage in hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(commandArr[0], commandArr.length > 1 ? commandArr[1] : "");
            FileManaging.writeToFile(CommandType.FILEPATH.toString(), tasks);
        } catch (DukeException e) {
            ui.displayToScreen(e.getMessage());
        } catch (IOException e) {
            ui.displayToScreen(e.getMessage());
        }
    }

    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage) {
        try {
            String output = tasks.addTask(commandArr[0], commandArr.length > 1 ? commandArr[1] : "");
            FileManaging.writeToFile(CommandType.FILEPATH.toString(), tasks);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
