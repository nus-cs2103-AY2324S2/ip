package commands;

import java.util.ArrayList;

import irwyn.tasks.Task;
import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

/**
 * This class encapsulates the class SortCommand.
 * It sorts the task list in storage.
 *
 * @author Irwyn Liong
 * @version Week-5
 */
public class SortCommand extends Command {
    /**
     * Constructor for a SortCommand object.
     *
     */
    SortCommand() {
        super(false);
    }

    /**
     * Executes the Sort Command.
     * Sorts the list then saves that new sorted list.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        taskList.sort();
        storageManager.save(taskList.getTasks());

        String reply = "Here are the sorted tasks in your list:\n";
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String num = i + 1 + ". ";
            Task task = tasks.get(i);
            reply += num + task + "\n";
        }

        ui.reply(reply);
    }

    /**
     * Executes the Sort command.
     * This method sorts the list then saves that new sorted list.
     *
     * @param storageManager Storage manager handles storing & deleting of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        taskList.sort();
        storageManager.save(taskList.getTasks());

        String reply = "Here are the sorted tasks in your list:\n";
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String num = i + 1 + ". ";
            Task task = tasks.get(i);
            reply += num + task + "\n";
        }

        return ui.getReply(reply);
    }
}
