package commands;

import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

/**
 * This class encapsulates the class EndCommand.
 * It ends the program.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class EndCommand extends Command {

    /**
     * Constructor for a EndCommand object.
     */
    EndCommand() {
        super(true);
    }

    /**
     * Executes the bye command.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        ui.end();
    }

    /**
     * Executes the bye command.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        return ui.getEnd();
    }
}
