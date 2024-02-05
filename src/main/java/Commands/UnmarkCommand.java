package Commands;

import Irwyn.Tasks.TaskList;
import Misc.StorageManager;
import Misc.Ui;

/**
 * This class encapsulates the class UnmarkCommand.
 * It unmarks the task on the list.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class UnmarkCommand extends Command {
    private final int unmark;

    /**
     * Constructor for a UnmarkCommand object.
     * @param input The input by the user to parse into a command.
     */
    UnmarkCommand (String input) {
        super(false);
        unmark = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the unmark command.
     * This method unmarks a Task from the list.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        taskList.unmark(unmark);
        storageManager.save(taskList.getTasks());
        String reply = "OK, I've marked this task as not done yet:\n" + taskList.getTask(unmark).toString() + "\n";
        ui.reply(reply);
    }
}
