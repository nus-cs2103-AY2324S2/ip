package commands;

import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

/**
 * This class encapsulates the class Unmark Command.
 * It unmarks the task on the list.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class UnmarkCommand extends Command {
    private final int unmark;

    /**
     * Constructor for a Unmark Command object.
     * @param input The input by the user to parse into a command.
     */
    UnmarkCommand(String input) {
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
        assert unmark <= taskList.getTasksSize() -1 : "Invalid index";
        taskList.unmark(unmark);
        storageManager.save(taskList.getTasks());
        String reply = "OK, I've marked this task as not done yet:\n" + taskList.getTask(unmark).toString() + "\n";
        ui.reply(reply);
    }

    /**
     * Executes the unmark command.
     * This method unmarks a Task from the list.
     *
     * @param storageManager Storage manager handles storing & deleting of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        taskList.unmark(unmark);
        storageManager.save(taskList.getTasks());
        String reply = "OK, I've marked this task as not done yet:\n" + taskList.getTask(unmark).toString() + "\n";
        return ui.getReply(reply);
    }
}
