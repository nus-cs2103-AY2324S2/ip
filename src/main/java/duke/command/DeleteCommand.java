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
public class DeleteCommand extends Command {
    private String[] commandArr;

    /**
     * Constructor of delete command.
     *
     * @param commandArr Array of commands.
     */
    public DeleteCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    /**
     * Executes delete command.
     *
     * @param tasks List of tasks.
     * @param ui Ui of ai chatbot.
     * @param storage External storage in hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(commandArr.length > 1 ? commandArr[1] : "");
            FileManaging.writeToFile(CommandType.FILEPATH.toString(), tasks);
        } catch (DukeException e) {
            ui.displayToScreen(e.getMessage());
        } catch (IOException e) {
            ui.displayToScreen(e.getMessage());
        }
    }
}
