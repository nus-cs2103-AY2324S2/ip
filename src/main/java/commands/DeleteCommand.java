package commands;

import irwyn.tasks.Task;
import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

/**
 * This class encapsulates the class DeleteCommand.
 * It executes the deletion of a Task.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class DeleteCommand extends Command {
    private final int delete;
    /**
     * Constructor for a DeleteCommand object.
     * @param input The input by the user to parse into a command.
     */
    public DeleteCommand(String input) {
        super(false);
        delete = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the delete command.
     * This method deletes a Task from the list.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        assert delete <= taskList.getTasksSize() - 1 : "Invalid index";
        Task t = taskList.delete(delete);
        storageManager.save(taskList.getTasks());
        String reply = "Noted. I've removed this task:\n"
                + t.toString() + "\n"
                + "Now you have " + taskList.getTasksSize()
                + " tasks in the list.\n";
        ui.reply(reply);
    }

    /**
     * Executes the delete command.
     * This method deletes a Task from the list.
     *
     * @param storageManager Storage manager handles storing & deleting of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        Task t = taskList.delete(delete);
        storageManager.save(taskList.getTasks());
        String reply = "Noted. I've removed this task:\n"
                + t.toString() + "\n"
                + "Now you have "
                + taskList.getTasksSize()
                + " tasks in the list.\n";
        return ui.getReply(reply);
    }
}
