package commands;

import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

/**
 * This class encapsulates the class MarkCommand.
 * It marks the task on the list.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class MarkCommand extends Command {
    private final int mark;

    /**
     * Constructor for a MarkCommand object.
     * @param input The input by the user to parse into a command.
     */
    public MarkCommand(String input) {
        super(false);
        mark = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the mark command.
     * This method marks a Task from the list.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        assert mark <= taskList.getTasksSize() - 1 : "Invalid index";
        taskList.mark(mark);
        storageManager.save(taskList.getTasks());
        String reply = "Nice! I've marked this task as done:\n" + taskList.getTask(mark).toString() + "\n";
        ui.reply(reply);
    }

    /**
     * Executes the mark command.
     * This method marks a Task from the list.
     *
     * @param storageManager Storage manager handles storing & deleting of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        taskList.mark(mark);
        storageManager.save(taskList.getTasks());
        String reply = "Nice! I've marked this task as done:\n" + taskList.getTask(mark).toString() + "\n";
        return ui.getReply(reply);
    }
}
