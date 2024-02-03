package duke.commands;

import java.io.IOException;

import duke.utils.TaskList;
import duke.utils.Storage;
/**
 * This class implements the exit command that leads to the exit of the bot when executed.
 *
 * @author delishad21
 */
public class ExitCommand extends Command {

    /**
     * Creates an exit command, sets isExit to true.
     */
    public ExitCommand() {
        super(true);
    }



    /**
     * Executes exit command, saves data into save file using storage object.
     *
     * @param tasks the current list of tasks.
     * @param ui Ui object used by bot for printing information.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            if (storage == null) {
                return "Data not saved: Storage initialisation not sucessful";
            }
            return storage.saveTodoData(tasks);
        } catch (IOException e) {
            return "Data not saved: " + e.getMessage();
        }
    }
}
